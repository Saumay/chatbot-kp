package com.trial.chatbotweb.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChatModel {
	private String text;
	private String language;
	private String sessionId;
}
