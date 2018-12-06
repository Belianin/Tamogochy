package tamagotchi;

import java.util.Date;

public abstract class Event {
    public Date when;
    
    protected Reply reply;
    public boolean hasReply()
    {
    	return reply != null;
    }
    
    public Reply getReply()
    {
    	return reply;
    }
    abstract boolean tryApply();
}