import java.util.concurrent.ThreadLocalRandom;

public class Demo {
    public static void main(String args[]){
        CharacterFactory creator = new CharacterFactory();
        Character c1 = creator.createCharacter();
        Character c2 = creator.createCharacter();
        GameManager fight_judge = new GameManager();
        fight_judge.fight(c1, c2);
    }
}

class CharacterFactory {
    Character createCharacter() {
        int to_spawn = ThreadLocalRandom.current().nextInt(0,4);
        switch (to_spawn) {
            case 1 :
                return new Elf();
            case 2 :
                return new Knight();
            case 3 :
                return new King();
        }
        return new Hobbit();
    }
}

class Hobbit extends Character{

    public Hobbit() {
        super(3, 0);
    }
    void kick(Character c) {
        toCry();
    }
    void toCry() {
    }
    public String toString() {
        return "Hobbit";
    }
}

class Elf extends Character{
    public Elf() {
        super(10, 10);
    }

    void kick(Character c) {
        if (c.power < this.power) {
            c.hp = 0;
        }
        else {
            c.power -= 1;
        }
    }
    public String toString() {
        return "Elf";
    }
}

class King extends Character{
    public King() {
        super(ThreadLocalRandom.current().nextInt(5, 16), 0);
    }
    void kick(Character c) {
        c.hp -= ThreadLocalRandom.current().nextInt(5, 16);
    }
    public String toString() {
        return "King";
    }
}

class Knight extends Character{
    public Knight() {
        super(ThreadLocalRandom.current().nextInt(2, 12), 0);
    }
    void kick(Character c) {
        c.hp -= ThreadLocalRandom.current().nextInt(2, 12);
    }
    public String toString() {
        return "Knight";
    }
}

class GameManager {
    void fight(Character c1, Character c2) {
        int i = 0;
        while (c1.hp > 0 && c2.hp > 0) {
            c1.kick(c2);
            c2.kick(c1);
            System.out.println("The #1 - " + c1 + "'s hp is " + c1.hp);
            System.out.println("The #2 - " + c2 + "'s hp is " + c2.hp);
            if (c1.isOut()) {
                System.out.println("The #1 - " + c1 + " is wiped out :(");
                break;
            } else if (c2.isOut()) {
                System.out.println("The #1 - " + c2 + " is wiped out :(");
                break;
            }
            i += 1;
            if (i > 10) {
                System.out.println("This is an eternal battle. Stop it!");
                break;
            }
        }
    }
}

class Character {
    int hp;
    int power;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Character(int hp, int power) {
        this.hp = hp;
        this.power = power;
    }

    void kick(Character c) {
    }
    boolean isOut() {
        return this.hp <= 0;
    }
}
