create database web_donation_tracker;
use web_donation_tracker;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `role_id` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `web_donation_tracker`.`user` (`user_name`, `password`, `full_name`, `email`, `address`, `phone_number`, `status`, `role_id`, `created_at`) VALUES 
('sherlock2311', '1', 'Ngô Xuân Lộc', 'loc@gmail.com', 'Hai Bà Trưng, Hà Nội', '0377870346', '1', '2', '2023-11-23 16:26:15'),
('truong', '1', 'Vương Hữu Trường', 'truong@gmail.com', 'Hai Bà Trưng, Hà Nội', '0377870346', '1', '1', '2023-11-23 16:26:15'),
('trang', '1', 'Nguyễn Thu Trang', 'trang@gmail.com', 'Hai Bà Trưng, Hà Nội', '0377870346', '1', '2', '2023-11-23 16:26:15');

CREATE TABLE `role` (
  `id` int NOT NULL,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `web_donation_tracker`.`role` (`role_name`) VALUES ('ADMIN'), ('USER');

CREATE TABLE `donation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `money` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` int DEFAULT NULL,
  `organization_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `web_donation_tracker`.`donation` (`code`, `name`, `description`, `money`, `start_date`, `end_date`, `status`, `organization_name`, `phone_number`, `created_at`) VALUES 
('TNVTĐ1', 'Gây quỹ trao tặng học bổng cho trẻ em nghèo khó khăn', 'Gây quỹ trao tặng 30 suất học bổng và xe đạp cho học sinh có hoàn cảnh khó khăn, học giỏi tỉnh Hà Tĩnh', '0', '2023-11-23', '2023-12-31', '1', 'CLB Tình nguyện viên Thủ đô', '0344215686', '2023-11-23 09:56:19'),
('QNBTT1', 'Gây quỹ Phép màu Y tế', 'Gây quỹ mang đến Phép Màu Y Tế cho 10 cuộc đời nhỏ dị tật bẩm sinh', '0', '2023-11-23', '2023-12-31', '2', 'CLB Tình nguyện viên Thủ đô', '0344215686', '2023-11-23 09:56:19'),
('OS1', 'Gây quỹ phẫu thuật hở hàm ếch cho trẻ em', 'Gây quỹ trao cơ hội phẫu thuật miễn phí cho 20 em bé hở môi, hàm ếch - Tháng 11/2023', '0', '2023-11-23', '2023-12-31', '1', 'CLB Tình nguyện viên Thủ đô', '0344215686', '2023-11-23 09:56:19'),
('DNXH1', 'Gây quỹ hỗ trợ chi phí phẫu thuật tim bẩm sinh tháng 11/2023', 'Gây quỹ hỗ trợ phẫu thuật tim bẩm sinh cho 7 em nhỏ có hoàn cảnh khó khăn - Tháng 11/2023', '0', '2023-11-23', '2023-12-31', '2', 'CLB Tình nguyện viên Thủ đô', '0344215686', '2023-11-23 09:56:19'),
('TTTL1', 'Tặng học bổng và truyền thông chống xâm hại trẻ em', 'Tặng học bổng và truyền thông chống xâm hại trẻ em tại trường THCS Giao Long, huyện Châu Thành, tỉnh Bến Tre', '0', '2023-11-23', '2023-12-31', '2', 'CLB Tình nguyện viên Thủ đô', '0344215686', '2023-11-23 09:56:19'),
('QHV1', 'Gây quỹ xây thêm nhà vệ sinh trường học', 'Gây quỹ xây dựng thêm 20 nhà vệ sinh trường học đã xuống cấp tại Sơn La và Thái Bình', '0', '2023-11-23', '2023-12-31', '2', 'CLB Tình nguyện viên Thủ đô', '0344215686', '2023-11-23 09:56:19');

CREATE TABLE `user_donation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `donation_id` int NOT NULL,
  `money` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` int DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_donation_ibfk_1` (`user_id`),
  KEY `user_donation_ibfk_2` (`donation_id`),
  CONSTRAINT `user_donation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_donation_ibfk_2` FOREIGN KEY (`donation_id`) REFERENCES `donation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;