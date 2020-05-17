package com.trial.chatbotweb.controller;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ReplyGenerator {
	private static final boolean TRACE_MODE = false;
	private static String botName = "bots/super";
	private Bot bot;
	private Chat chatSession;
	
	ReplyGenerator() {
		String resourcesPath = getResourcesPath();
		System.out.println(resourcesPath);
		bot = new Bot("super", resourcesPath);
		bot.writeAIMLFiles();
		chatSession = new Chat(bot);
		bot.brain.nodeStats();
	}

	String generateReply(String message) {
		try {
			System.out.print("Human : ");
			if ((message == null) || (message.length() < 1))
				message = MagicStrings.null_input;
			if (message.equals("q")) {
				System.exit(0);
			} else if (message.equals("wq")) {
				bot.writeQuit();
				System.exit(0);
			} else {
				String request = message;
				if (MagicBooleans.trace_mode)
					System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
				String response = chatSession.multisentenceRespond(request);
				while (response.contains("&lt;"))
					response = response.replace("&lt;", "<");
				while (response.contains("&gt;"))
					response = response.replace("&gt;", ">");
				System.out.println("Robot : " + response);
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "It seems I am not fine. Leave me alone.";
}

	private static String getResourcesPath() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 2);
		System.out.println(path);
		return path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
	}
}