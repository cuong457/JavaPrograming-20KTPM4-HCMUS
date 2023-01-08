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
        select send, sender_name, receive, receiver_name, message, send_at 
        from (select * from chat_history chat
            where chat.receive = group_id
                and chat.send_at > (select dlt_chat.delete_at 
                            from delete_chat_time_stamp dlt_chat 
                            where dlt_chat.chat_from = user_id
                                and dlt_chat.chat_to = group_id limit 1)) chat_his
        left join (select id, name sender_name from users) nameSend on nameSend.id = chat_his.send
        left join (select id, group_name receiver_name from group_chat) nameRecv on nameRecv.id = chat_his.receive
        order by send_at;
    else
        select send, sender_name, receive, receiver_name, message, send_at 
        from (select * from chat_history chat
                where chat.receive = group_id) chat_his
        left join (select id, name sender_name from users) nameSend on nameSend.id = chat_his.send
        left join (select id, group_name receiver_name from group_chat) nameRecv on nameRecv.id = chat_his.receive
        order by send_at;
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
        select send, sender_name, receive, receiver_name, message, send_at 
        from (select * from chat_history chat
            where (chat.receive = send_id or chat.receive = receive_id)
                and chat.send_at > (select dlt_chat.delete_at 
                            from delete_chat_time_stamp dlt_chat 
                            where dlt_chat.chat_from = send_id
                                and dlt_chat.chat_to = receive_id limit 1)) chat_his
        left join (select id, name sender_name from users) nameSend on nameSend.id = chat_his.send
        left join (select id, name receiver_name from users) nameRecv on nameRecv.id = chat_his.receive
        order by send_at;
    else
        select send, sender_name, receive, receiver_name, message, send_at 
        from (select * from chat_history chat
                where (chat.receive = send_id or chat.receive = receive_id)) chat_his
        left join (select id, name sender_name from users) nameSend on nameSend.id = chat_his.send
        left join (select id, name receiver_name from users) nameRecv on nameRecv.id = chat_his.receive
        order by send_at;
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
	in receive_id varchar(8),
    in messages varchar(500) charset utf8
)
begin 
	insert into chat_history values (send_id, receive_id, messages, DATE_FORMAT(NOW(), '%Y-%m-%d %T'));
end //
DELIMITER ;

drop procedure if exists getFriendsAndGroups;
DELIMITER //
create procedure getFriendsAndGroups (
    in user_id varchar(8)
)
begin
    select distinct(receive) as id, group_name as name, image
    from chat_history ch join group_chat gc on gc.id = ch.receive
    where send = user_id
    union
    select distinct(receive) as id, name, image
    from chat_history ch join users u on u.id = ch.receive
    where send = user_id;
end //
DELIMITER ;

drop procedure if exists getAllHistory;
DELIMITER //
create procedure getAllHistory(
    in user_id varchar(8)
)
begin
    select send, name as sender_name, receive, (select distinct(group_name) from group_chat where id = receive) as receiver_name, message
    from chat_history ch join users u on ch.send = u.id 
    where receive like 'group%' and send=user_id
    union
    select send, name as sender_name, receive, (select distinct(name) from users where id = receive) as receiver_name, message
    from chat_history ch join users u on ch.send = u.id 
    where receive like 'user%' and send=user_id;
end //
DELIMITER ;

call getAllHistory('user_2');

call getFriendsAndGroups('user_2');

call getGroupChatHistory('user_2', 'group_1'); 

call getPrivateChatHistory('user_1', 'user_2');

call getPrivateChatHistory('user_2', 'user_1');

call deleteChatHistory('user_1', 'user_2');



select * from java_chatting_app.group_chat;
select * from java_chatting_app.chat_history;
select * from java_chatting_app.users;



