package main.Logic.Abilities.Basics;

import java.util.ArrayList;

import main.Logic.Abilities.Ability;
import main.Logic.ElementSystem.Element;
import main.Logic.Map.Cell;
import main.Logic.Unit.Statistic;
import main.Logic.Unit.Unit;

public class Punch implements Ability{

    private double Damage = 5;
    private int AOE = 0;
    private double Chance = 100;
    private Statistic scalingStat = null;
    private double manaCost = 0;
    private int range  = 1;
    private String name = "Punch";

    private Unit owner = null;

    public Punch(Unit owner){
        this.owner = owner;
        if(this.owner!=null){
            this.scalingStat = this.owner.getSTRENGHT();
            this.owner.addAbility(this);
        }
    }

    public void AffectTarget(Unit target){
        if(this.canHitTarget(target)){
            this.owner.reduceMana(this.manaCost);
            double dmg = this.getDamageToTarget(target);
            target.takeDamage(dmg);
        }
    }

    public boolean canHitTarget(Unit target){
        if (this.owner == null) {
            return false;
        }
        if(target==null){
            return false;
        }
        if(this.owner.getPosition()==null){
            return false;
        }
        boolean ret = this.canHitCell(target.getPosition());
        if(!ret){
            return false;
        }
        if(this.owner.getMP()-this.manaCost<0){
            return false;
        }
        if(this.owner.isDead()){
            return false;
        }
        if(target.isDead()){
            return false;
        }
        if(target==this.owner){
            return false;
        }
        return true;
    }

    public double getBaseDamage(){
        return this.Damage;
    }

    public int getRange(){
        return this.range;
    }

    public boolean isElemental(){
        return false;
    }

    public main.Logic.ElementSystem.Element.type getType(){
        return Element.type.DAMAGE;
    }

    public main.Logic.ElementSystem.Element.DamageElement getDamageElement(){
        return null;
    }

    public main.Logic.ElementSystem.Element.DamageType getDamageType(){
        return Element.DamageType.Physical;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Element.type> getTraits(){
        return null;
    }

    public Unit getOwner(){
        return this.owner;
    }

    public Statistic getScalingStat(){
        return this.scalingStat;
    }

    public double getDamageToTarget(Unit target){
        double dmg = this.Damage;
        dmg*=this.scalingStat.EffectiveValue;
        double dodge = target.generateDodgeVal();
        if(dodge>this.Chance){
            dmg = 0;
        }else if(dodge == 1){
            dmg*=2;
        }
        dmg-=target.getArmor();
        if(dmg<0){
            dmg=0;
        }
        return dmg;
    }

    public double getHitChance(){
        return this.Chance;
    }

    public int getAOE(){
        return 0;
    }

    public double getManaCost(){
        return this.manaCost;
    }

    public boolean canHitCell(Cell cell){
        double dist = this.owner.getPosition().distanceToCell(cell);
        if(dist > this.range){
            return false;
        }
        if(dist == -1){
            return false;
        }
        return true;
    }
}
