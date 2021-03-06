package tamagotchi;

import java.util.Date;

public class DeathEvent extends Event {
    private Pet pet;
    public static int minDeathTime = 15000;

    public DeathEvent(Pet pet) {
        this.pet = pet;
        Date currentDate = new Date();
        this.when = new Date(currentDate.getTime() + minDeathTime);
    }

    @Override
    public boolean tryApply() {
    	int coef = 1;//getDeathCount();

    	pet.addHunger(-6 * coef);
    	pet.addClean(-5 * coef);
    	if (!pet.isSleep)
    		pet.addSleep(-2 * coef);
    	pet.addToilet(-6 * coef);
    	pet.setLastUpdate(new Date());
    	
    	when = new Date(when.getTime() + minDeathTime);
    	
    	if (pet.isResponsible) {
    		takeCare();
    	}
    	
    	
		if (pet.getHunger() == 0 || pet.getHealth() == 0) {
			pet.alive = false;
			pet.master.pet = null;
			pet.master.events.clear();
			reply = new Reply(pet.name + " RIP :(", DialogName.Start);
		}

    	return false;
    }
    
    private void takeCare() {
    	StringBuilder sb = new StringBuilder();
    	
    	if (pet.getHunger() < 30 && pet.master.money >= 15) {
    		pet.addHunger(40);
    		pet.master.money -= 15;
    		sb.append("Почувствовав голод, " + pet.name + " купил себе пирожок за 15 монет.\n");
    	}
    	
    	if (pet.getHealth() < 30 && pet.master.money >= 20) {
    		pet.addHunger(35);
    		pet.master.money -= 20;
    		sb.append("Почувствовав себя нехорошо, " + pet.name + " выпил таблеток на 20 монет.\n");
    	}
    	
    	if (sb.length() > 0)
    		reply = new Reply(sb.toString());
    }
    
	private int getDeathCount() {
		Date currentDate = new Date();
		long difference = currentDate.getTime() - when.getTime();
		int sec = (int) (difference / 1000);
		return sec / 10;
	}
}
