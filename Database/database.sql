DROP DATABASE IF EXISTS Java_chatting_app;
CREATE SCHEMA Java_chatting_app;

use Java_chatting_app;

CREATE TABLE user_login_history
(
	his_id VARCHAR(45) NOT NULL,
	user_id VARCHAR(45) NOT NULL,
	device_name VARCHAR(45) NOT NULL,
	location VARCHAR(45) NOT NULL,
	online_status VARCHAR(45) NOT NULL,
	device_icon VARCHAR(45) NOT NULL,
	
    PRIMARY KEY (his_id, user_id)
);

CREATE TABLE users
(
	id VARCHAR(8) NOT NULL,
    name VARCHAR(45),
    image VARCHAR(500),
    bg VARCHAR(45),
    online_status VARCHAR(60),
    usn VARCHAR(20),
    psw VARCHAR(100),
    address VARCHAR(100),
    dob VARCHAR(45),
    sex VARCHAR(7),
    email VARCHAR(100),
    nofFriends varchar(3),
    friend varchar(500),
    ban_status varchar(10),
    createAt varchar(45),

	PRIMARY KEY (id)
);

INSERT INTO users (id,ban_status,name,image,bg,online_status,usn,psw,address,dob,sex,email,nofFriends,friend,createAt) VALUES ('user_3', 'normal', 'Nguyen Ba Phuong', './assets/imgs/avts/avt_1.png', './assets/imgs/bgs/bg-1.png', 'Last online November 27 at 11:28 AM', 'nguyenbaphuong','123456', 'unknown', 'unknown', 'female', 'unknown', '11', 'user_1/user_5/user_2/user_6/user_7/user_9/user_10/user_11/user_12/user_4/user_12', '2023/01/06 11:12:42');
INSERT INTO users (id,ban_status,name,image,bg,online_status,usn,psw,address,dob,sex,email,nofFriends,friend,createAt) VALUES ('user_2', 'normal', 'Phan Phuc Dat', './assets/imgs/avts/avt_2.png', './assets/imgs/bgs/bg-2.png', 'Online now', 'PhucDat2808','123456', 'Hamlet Binh Tien 1, lo 825, Xa Duc Hoa Ha, Long An', '28-8-2002', 'male', 'pd2808@gmail.com', '6', 'user_1/user_3/user_2/user_7/user_10/user_4', '2022/12/09 01:16:35');
INSERT INTO users (id,ban_status,name,image,bg,online_status,usn,psw,address,dob,sex,email,nofFriends,friend,createAt) VALUES ('user_1', 'normal', 'Phan Duong Minh', './assets/imgs/avts/avt_3.png', './assets/imgs/bgs/bg-3.png', 'Last online about an hour ago', 'minhminh0410','123456', '27/476 Cho Hang Moi Street, Du Hang Kenh Ward, Hai Phong City', '4-10-2002', 'male', 'dm410@gmail.com', '8', 'user_3/user_2/user_6/user_11/user_12/user_4', '2022/03/14 15:41:58');
INSERT INTO users (id,ban_status,name,image,bg,online_status,usn,psw,address,dob,sex,email,nofFriends,friend,createAt) VALUES ('user_7', 'normal', 'Earl E. Bird', './assets/imgs/avts/avt_4.png', './assets/imgs/bgs/bg-4.png', 'Last online August 30 at 9:44 PM', 'EarlEBird','123456', '1C/236 Le Trong Tan, Khuong Mai Ward, Thanh Xuan Dist, Hanoi', '7-8-1987', 'male', 'EarlEBird@gmail.com', '7', 'user_1/user_2/user_3/user_5/user_4/user_7/user_9', '2022/09/18 12:17:58');
INSERT INTO users (id,ban_status,name,image,bg,online_status,usn,psw,address,dob,sex,email,nofFriends,friend,createAt) VALUES ('user_10', 'normal', 'Biff Wellington', './assets/imgs/avts/avt_5.png', './assets/imgs/bgs/bg-5.png', 'Last online August 14 at 12:38 AM', 'BiffWellington','123456', 'Lot 32, An Don Industrial Park, Da Nang City', '7-8-1987', 'male', 'BiffWellington@gmail.com', '7', 'user_1/user_2/user_3/user_5/user_4/user_7/user_9', '2022/09/02 21:40:55');
INSERT INTO users (id,ban_status,name,image,bg,online_status,usn,psw,address,dob,sex,email,nofFriends,friend,createAt) VALUES ('user_6', 'normal', 'Phan Duong Tung Anh', './assets/imgs/avts/avt_6.png', './assets/imgs/bgs/bg-6.png', 'Last online about an hour ago', 'tunganhdaddy','123456', '37/8A Quang Trung, Ward 10, Go Vap District, Ho Chi Minh City', '7-8-1987', 'male', 'tunganh@gmail.com', '7', 'user_1/user_2/user_3/user_5/user_4/user_7/user_9', '2022/03/06 03:59:17');
INSERT INTO users (id,ban_status,name,image,bg,online_status,usn,psw,address,dob,sex,email,nofFriends,friend,createAt) VALUES ('user_5', 'normal', 'Ngo Anh Hung', './assets/imgs/avts/avt_7.png', './assets/imgs/bgs/bg-7.png', 'Last online 23 hours ago', 'anhhungngo','123456', '18B Cong Hoa Street, Ward 4, Tan Binh District, Ho Chi Minh City', '28-2-2000', 'male', 'anhhungngo@gmail.com', '4', 'user_4/user_10/user_1/user_3', '2022/11/22 06:36:27');
INSERT INTO users (id,ban_status,name,image,bg,online_status,usn,psw,address,dob,sex,email,nofFriends,friend,createAt) VALUES ('user_4', 'normal', 'Hoang Dieu Linh', './assets/imgs/avts/avt_8.png', './assets/imgs/bgs/bg-8.png', 'Last online October 30 at 9:55 PM', 'dieulinh113','123456', '1/18 Su Van Hanh Street, Ward 12, District 10, Ho Chi Minh City', '27-8-2003', 'female', 'dl1130@gmail.com', '10', 'user_3/user_1/user_5/user_2/user_6/user_7/user_9/user_10/user_11/user_12', '2022/07/13 15:57:11');
INSERT INTO users (id,ban_status,name,image,bg,online_status,usn,psw,address,dob,sex,email,nofFriends,friend,createAt) VALUES ('user_11', 'normal', 'Barb E. Dahl', './assets/imgs/avts/avt_9.png', './assets/imgs/bgs/bg-9.png', 'Last online August 14 at 12:38 AM', 'BarbEDahl','123456', 'Floor 8, Sun Wah Tower 115 Nguyen Hue Street , Ben Nghe Ward, Ho Chi Minh City', '7-8-1987', 'male', 'BarbEDahl@gmail.com', '7', 'user_1/user_2/user_3/user_5/user_4/user_7/user_9', '2022/10/14 12:28:47');
INSERT INTO users (id,ban_status,name,image,bg,online_status,usn,psw,address,dob,sex,email,nofFriends,friend,createAt) VALUES ('user_9', 'normal', 'Charity Case', './assets/imgs/avts/avt_10.png', './assets/imgs/bgs/bg-10.png', 'Last online August 17 at 3:57 PM', 'CharityCase','123456', '12M Nguyen Thi Minh Khai, Da Kao, Dist.1, Ho Chi Minh City', '7-8-1987', 'male', 'CharityCase@gmail.com', '7', 'user_1/user_2/user_3/user_5/user_4/user_7/user_9', '2022/08/31 07:05:50');
INSERT INTO users (id,ban_status,name,image,bg,online_status,usn,psw,address,dob,sex,email,nofFriends,friend,createAt) VALUES ('user_12', 'normal', 'Adam Zapel', './assets/imgs/avts/avt_11.png', './assets/imgs/bgs/bg-11.png', 'Last online August 9 at 12:22 PM', 'AdamZapel','123456', 'Floor 6, 8 Nguyen Hue, Ben Nghe Ward, Ho Chi Minh City', '7-8-1987', 'male', 'AdamZapel@gmail.com', '7', 'user_1/user_2/user_3/user_5/user_4/user_7/user_9', '2022/12/10 17:31:33');
INSERT INTO users (id,ban_status,name,image,bg,online_status,usn,psw,address,dob,sex,email,nofFriends,friend,createAt) VALUES ('user_8', 'normal', 'Don Key', './assets/imgs/avts/avt_12.png', './assets/imgs/bgs/bg-12.png', 'Last online August 17 at 10:42 PM', 'DonKey','123456', '68 Group 1, hamlet 1a, Loc Ninh Town, Binh Phuoc', '7-8-1987', 'male', 'DonKey@gmail.com', '7', 'user_1/user_2/user_3/user_5/user_4/user_7/user_9', '2022/09/13 16:15:30');

INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_1', 'user_3', 'Windows PC', 'Ho Chi Minh City, Vietnam', 'Active now', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_2', 'user_3', 'iMac', 'Ho Chi Minh City, Vietnam', 'about an hour ago', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_3', 'user_3', 'Linux PC', 'Ho Chi Minh City, Vietnam', 'Yesterday at 9:54 PM', 'linux');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_4', 'user_3', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 27 at 11:28 AM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_5', 'user_3', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 5 at 2:38 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_6', 'user_3', 'iMac', 'Ho Chi Minh City, Vietnam', 'October 30 at 9:55 PM', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_7', 'user_3', 'Window PC', 'Ho Chi Minh City, Vietnam', 'October 15 at 9:15 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_1', 'user_2', 'Windows PC', 'Ho Chi Minh City, Vietnam', 'Active now', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_2', 'user_2', 'iMac', 'Ho Chi Minh City, Vietnam', 'about an hour ago', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_3', 'user_2', 'Linux PC', 'Ho Chi Minh City, Vietnam', 'Yesterday at 9:54 PM', 'linux');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_4', 'user_2', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 27 at 11:28 AM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_5', 'user_2', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 5 at 2:38 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_6', 'user_2', 'iMac', 'Ho Chi Minh City, Vietnam', 'October 30 at 9:55 PM', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_7', 'user_2', 'Window PC', 'Ho Chi Minh City, Vietnam', 'October 15 at 9:15 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_1', 'user_1', 'Window PC', 'Ho Chi Minh City, Vietnam', 'Active now', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_2', 'user_1', 'iMac', 'Ho Chi Minh City, Vietnam', 'about an hour ago', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_3', 'user_1', 'Linux PC', 'Ho Chi Minh City, Vietnam', 'Yesterday at 9:54 PM', 'linux');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_4', 'user_1', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 27 at 11:28 AM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_5', 'user_1', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 5 at 2:38 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_6', 'user_1', 'iMac', 'Ho Chi Minh City, Vietnam', 'October 30 at 9:55 PM', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_7', 'user_1', 'Window PC', 'Ho Chi Minh City, Vietnam', 'October 15 at 9:15 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_1', 'user_7', 'Window PC', 'Ho Chi Minh City, Vietnam', 'Active now', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_2', 'user_7', 'iMac', 'Ho Chi Minh City, Vietnam', 'about an hour ago', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_3', 'user_7', 'Linux PC', 'Ho Chi Minh City, Vietnam', 'Yesterday at 9:54 PM', 'linux');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_4', 'user_7', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 27 at 11:28 AM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_5', 'user_7', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 5 at 2:38 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_6', 'user_7', 'iMac', 'Ho Chi Minh City, Vietnam', 'October 30 at 9:55 PM', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_7', 'user_7', 'Window PC', 'Ho Chi Minh City, Vietnam', 'October 15 at 9:15 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_1', 'user_6', 'Window PC', 'Ho Chi Minh City, Vietnam', 'Active now', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_2', 'user_6', 'iMac', 'Ho Chi Minh City, Vietnam', 'about an hour ago', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_3', 'user_6', 'Linux PC', 'Ho Chi Minh City, Vietnam', 'Yesterday at 9:54 PM', 'linux');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_4', 'user_6', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 27 at 11:28 AM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_5', 'user_6', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 5 at 2:38 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_6', 'user_6', 'iMac', 'Ho Chi Minh City, Vietnam', 'October 30 at 9:55 PM', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_7', 'user_6', 'Window PC', 'Ho Chi Minh City, Vietnam', 'October 15 at 9:15 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_1', 'user_10', 'Window PC', 'Ho Chi Minh City, Vietnam', 'Active now', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_2', 'user_10', 'iMac', 'Ho Chi Minh City, Vietnam', 'about an hour ago', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_3', 'user_10', 'Linux PC', 'Ho Chi Minh City, Vietnam', 'Yesterday at 9:54 PM', 'linux');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_4', 'user_10', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 27 at 11:28 AM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_5', 'user_10', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 5 at 2:38 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_6', 'user_10', 'iMac', 'Ho Chi Minh City, Vietnam', 'October 30 at 9:55 PM', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_7', 'user_10', 'Window PC', 'Ho Chi Minh City, Vietnam', 'October 15 at 9:15 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_1', 'user_5', 'Window PC', 'Ho Chi Minh City, Vietnam', 'Active now', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_2', 'user_5', 'iMac', 'Ho Chi Minh City, Vietnam', 'about an hour ago', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_3', 'user_5', 'Linux PC', 'Ho Chi Minh City, Vietnam', 'Yesterday at 9:54 PM', 'linux');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_4', 'user_5', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 27 at 11:28 AM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_5', 'user_5', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 5 at 2:38 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_6', 'user_5', 'iMac', 'Ho Chi Minh City, Vietnam', 'October 30 at 9:55 PM', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_7', 'user_5', 'Window PC', 'Ho Chi Minh City, Vietnam', 'October 15 at 9:15 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_1', 'user_4', 'Window PC', 'Ho Chi Minh City, Vietnam', 'Active now', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_2', 'user_4', 'iMac', 'Ho Chi Minh City, Vietnam', 'about an hour ago', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_3', 'user_4', 'Linux PC', 'Ho Chi Minh City, Vietnam', 'Yesterday at 9:54 PM', 'linux');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_4', 'user_4', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 27 at 11:28 AM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_5', 'user_4', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 5 at 2:38 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_6', 'user_4', 'iMac', 'Ho Chi Minh City, Vietnam', 'October 30 at 9:55 PM', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_7', 'user_4', 'Window PC', 'Ho Chi Minh City, Vietnam', 'October 15 at 9:15 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_1', 'user_9', 'Window PC', 'Ho Chi Minh City, Vietnam', 'Active now', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_2', 'user_9', 'iMac', 'Ho Chi Minh City, Vietnam', 'about an hour ago', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_3', 'user_9', 'Linux PC', 'Ho Chi Minh City, Vietnam', 'Yesterday at 9:54 PM', 'linux');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_4', 'user_9', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 27 at 11:28 AM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_5', 'user_9', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 5 at 2:38 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_6', 'user_9', 'iMac', 'Ho Chi Minh City, Vietnam', 'October 30 at 9:55 PM', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_7', 'user_9', 'Window PC', 'Ho Chi Minh City, Vietnam', 'October 15 at 9:15 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_1', 'user_11', 'Window PC', 'Ho Chi Minh City, Vietnam', 'Active now', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_2', 'user_11', 'iMac', 'Ho Chi Minh City, Vietnam', 'about an hour ago', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_3', 'user_11', 'Linux PC', 'Ho Chi Minh City, Vietnam', 'Yesterday at 9:54 PM', 'linux');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_4', 'user_11', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 27 at 11:28 AM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_5', 'user_11', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 5 at 2:38 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_6', 'user_11', 'iMac', 'Ho Chi Minh City, Vietnam', 'October 30 at 9:55 PM', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_7', 'user_11', 'Window PC', 'Ho Chi Minh City, Vietnam', 'October 15 at 9:15 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_1', 'user_8', 'Window PC', 'Ho Chi Minh City, Vietnam', 'Active now', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_2', 'user_8', 'iMac', 'Ho Chi Minh City, Vietnam', 'about an hour ago', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_3', 'user_8', 'Linux PC', 'Ho Chi Minh City, Vietnam', 'Yesterday at 9:54 PM', 'linux');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_4', 'user_8', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 27 at 11:28 AM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_5', 'user_8', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 5 at 2:38 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_6', 'user_8', 'iMac', 'Ho Chi Minh City, Vietnam', 'October 30 at 9:55 PM', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_7', 'user_8', 'Window PC', 'Ho Chi Minh City, Vietnam', 'October 15 at 9:15 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_1', 'user_12', 'Window PC', 'Ho Chi Minh City, Vietnam', 'Active now', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_2', 'user_12', 'iMac', 'Ho Chi Minh City, Vietnam', 'about an hour ago', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_3', 'user_12', 'Linux PC', 'Ho Chi Minh City, Vietnam', 'Yesterday at 9:54 PM', 'linux');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_4', 'user_12', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 27 at 11:28 AM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_5', 'user_12', 'Window PC', 'Ho Chi Minh City, Vietnam', 'November 5 at 2:38 PM', 'window');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_6', 'user_12', 'iMac', 'Ho Chi Minh City, Vietnam', 'October 30 at 9:55 PM', 'mac');
INSERT INTO user_login_history (his_id,user_id,device_name,location,online_status,device_icon) VALUES ('his_7', 'user_12', 'Window PC', 'Ho Chi Minh City, Vietnam', 'October 15 at 9:15 PM', 'window');

use Java_chatting_app;

create table group_chat 
(
	id varchar(8) not null,
	group_name varchar(60),
	image varchar(100),
    createdAt varchar(45),

	primary key (id)
);

create table group_chat_users
(
	group_id varchar(8),
    user_id varchar(8),

	primary key (group_id, user_id)
);

insert into group_chat values ('group_1', 'Lap trinh Java', '', '01-07-2023');
insert into group_chat values ('group_2', 'Lap trinh Web', '', '01-07-2023');

insert into group_chat_users values ('group_1', 'user_1');
insert into group_chat_users values ('group_1', 'user_3');
insert into group_chat_users values ('group_1', 'user_5');

insert into group_chat_users values ('group_2', 'user_1');
insert into group_chat_users values ('group_2', 'user_2');
insert into group_chat_users values ('group_2', 'user_4');
insert into group_chat_users values ('group_2', 'user_6');

create table chat_history
(
	send varchar(8),
    receive varchar(8),
	message varchar(500) charset utf8,
    send_at varchar(45),
	
    primary key (send, receive, message, send_at)
);

create table delete_chat_time_stamp
(
	chat_from varchar(8),
	chat_to varchar(8),
    delete_at varchar(45),
	
    primary key (chat_from, chat_to, delete_at)
);

-- DATE_FORMAT(NOW(), '%Y-%m-%d %T') 
insert into chat_history values ('user_1', 'group_1', 'hé lô mấy cưng', '2023-01-08 00:13:37');
insert into chat_history values ('user_2', 'group_1', 'Lô con kẹc', '2023-01-08 00:14:41');
insert into chat_history values ('user_1', 'group_1', 'Đảk vậy đ.chí', '2023-01-08 00:15:20');

insert into chat_history values ('user_2', 'group_2', 'Chào mọi người', '2023-01-08 00:20:42');

insert into chat_history values ('user_2', 'user_1', 'Hi em', '2023-01-08 00:13:41');
insert into chat_history values ('user_2', 'user_1', 'Em ăn cơm chưa', '2023-01-08 00:13:45');
insert into chat_history values ('user_1', 'user_2', 'Seen', '2023-01-09 09:13:42');

insert into delete_chat_time_stamp values ('user_1', 'group_1', '2023-01-08 00:15:00');
-- insert into delete_chat_time_stamp values ('user_1', 'user_2', '2023-01-10 00:15:00');


















