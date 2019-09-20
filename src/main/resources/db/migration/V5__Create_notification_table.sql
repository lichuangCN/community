CREATE TABLE `notification` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`notifier_id` BIGINT DEFAULT '0' COMMENT '发起通知的人id',
	`receiver_id` BIGINT DEFAULT '0' COMMENT '被通知的人id',
	`notify_outer_id` BIGINT DEFAULT '0' COMMENT '问题/评论的id',
	`notify_type` INT DEFAULT '0' COMMENT '区分通知类型:问题/评论',
	`gmt_create` BIGINT DEFAULT '0' COMMENT '创建通知的时间',
	`status` INT DEFAULT '0' COMMENT '0:未读,1:已读',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=utf8;