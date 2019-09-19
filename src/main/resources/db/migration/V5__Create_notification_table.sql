CREATE TABLE `notification` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`notifier` BIGINT DEFAULT '0' COMMENT '发起通知的人',
	`receiver` BIGINT DEFAULT '0' COMMENT '被通知的人',
	`outer` BIGINT DEFAULT '0' COMMENT '问题/评论的id',
	`type` INT DEFAULT '0' COMMENT '区分通知类型:问题/评论',
	`gmt_create` BIGINT DEFAULT '0' COMMENT '创建通知的时间',
	`status` INT DEFAULT '0' COMMENT '0:未读,1:已读',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=utf8;