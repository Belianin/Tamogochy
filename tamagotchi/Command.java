package tamagotchi;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
	protected List<String> synonyms = new ArrayList<String>();
	protected Reply defaultReply;
	
	public abstract Reply reply(UserData user, String input);

	public boolean matchInput(String input) {
		return synonyms.contains(input.toLowerCase());
	}
}
