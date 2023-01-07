use java_chatting_app;

drop procedure if exists getUsersOfGroup;
DELIMITER //
create procedure getUsersOfGroup (
	in group_id varchar(8)
)
begin
	select gp.user_id from group_chat_users gp
	where gp.group_id = group_id;
end //
DELIMITER ;


drop procedure if exists getGroupChatHistory;
DELIMITER //
create procedure getGroupChatHistory (
	in group_id varchar(8)
)
begin
	select * from group_chat_history gp
	where gp.group_id = group_id;
end //
DELIMITER ;

use java_chatting_app;
drop procedure if exists getGroupChatHistory;
DELIMITER //
create procedure getGroupChatHistory (
	in user_id varchar(8),
	in group_id varchar(8)
)
begin
	if exists (select * from delete_chat_time_stamp
				where chat_from = user_id 
                and chat_to = group_id)
	then
		select * from chat_history chat
		where chat.receive = group_id
			and chat.send_at > (select dlt_chat.delete_at 
						from delete_chat_time_stamp dlt_chat 
						where dlt_chat.chat_from = user_id
							and dlt_chat.chat_to = group_id limit 1)
		order by chat.send_at;
    else
		select * from chat_history chat
		where chat.receive = group_id
        order by chat.send_at;
    end if;
end //
DELIMITER ;

drop procedure if exists getPrivateChatHistory;
DELIMITER //
create procedure getPrivateChatHistory (
	in send_id varchar(8),
	in receive_id varchar(8)
)
begin
	if exists (select * from delete_chat_time_stamp
				where chat_from = send_id 
                and chat_to = receive_id)
	then
		select * from chat_history chat
		where (chat.receive = send_id or chat.receive = receive_id)
			and chat.send_at > (select dlt_chat.delete_at 
						from delete_chat_time_stamp dlt_chat 
						where dlt_chat.chat_from = send_id
							and dlt_chat.chat_to = receive_id limit 1)
		order by chat.send_at;
    else
		select * from chat_history chat
		where (chat.receive = send_id or chat.receive = receive_id)
        order by chat.send_at;
    end if;
end //
DELIMITER ;

drop procedure if exists deleteChatHistory;
DELIMITER //
create procedure deleteChatHistory (
	in send_id varchar(8),
	in receive_id varchar(8)
)
begin 
	if exists (select * from delete_chat_time_stamp
				where chat_from = send_id 
                and chat_to = receive_id)
	then
		update delete_chat_time_stamp set delete_at = DATE_FORMAT(NOW(), '%Y-%m-%d %T');
	else
		insert into delete_chat_time_stamp values (send_id, receive_id, DATE_FORMAT(NOW(), '%Y-%m-%d %T'));
    end if;
end //
DELIMITER ;



drop procedure if exists addChat;
DELIMITER //
create procedure addChat (
	in send_id varchar(8),
	in receive_id varchar(8)
)
begin 
	
end //
DELIMITER ;



call getGroupChatHistory('user_1', 'group_1'); 

call getPrivateChatHistory('user_1', 'user_2');

call getPrivateChatHistory('user_2', 'user_1');

call deleteChatHistory('user_1', 'user_2');



