package Logic;

import java.util.ArrayList;
import java.util.PriorityQueue;

import main.Logic.Map.Map;
import main.Logic.Unit.Unit;

public class GameController {
    private ArrayList<Unit>enemies = new ArrayList<Unit>();
    private ArrayList<Unit>allies = new ArrayList<Unit>();
    private PriorityQueue<Unit>units = new PriorityQueue<Unit>();
    private Difficulty.DifficultyStage currentDifficulty = null;
    private int numberOfChars = 0;
    private Unit currentChar = null;
    private Map gameMap = null;

    public GameController(Unit[] allies,Unit[] enemies,Difficulty.DifficultyStage difficultyStage,Map map){
        this.gameMap = map;
        this.currentDifficulty = difficultyStage;
        for(int i=0;i<allies.length;++i){
            allies[i].setIsAlly(true);
            this.allies.add(allies[i]);
            this.units.add(allies[i]);
            numberOfChars++;
        }
        for(int i=0;i<enemies.length;++i){
            enemies[i].setIsAlly(false);
            this.enemies.add(enemies[i]);
            this.units.add(enemies[i]);
            numberOfChars++;
        }
        this.currentChar = this.units.peek();
        this.startNextTurn();
    }

    public Unit getCurrentChar(){
        return this.currentChar;
    }

    public Map getGameMap(){
        return this.gameMap;
    }

    public int getNumberOfChars(){
        return this.numberOfChars;
    }

    public Difficulty.DifficultyStage getCurrentDifficulty(){
        return this.currentDifficulty;
    }

    private boolean rebuildUnitQueue(){
        for(int i=0;i<this.allies.size();++i){
            if(!this.allies.get(i).isDead()){
                this.units.add(this.allies.get(i));
            }
        }
        for(int i=0;i<this.enemies.size();++i){
            if(!this.enemies.get(i).isDead()){
                this.units.add(this.enemies.get(i));
            }
        }
        if(this.units.size()==0||this.units.size()==1){
            return false;
        }
        return true;
    }

    public boolean hasWon(){
        if(this.allies.size()!=0){
            if(this.enemies.size()==0){
                return true;
            }
        }
        return false;
    }

    public boolean hasLost(){
        if(this.allies.size()==0){
            if(this.enemies.size()!=0){
                return true;
            }
        }
        return false;
    }

    private void startNextTurn(){
        this.currentChar = this.units.peek();
        if(this.currentChar!=null){
            if(!this.currentChar.isDead()) {
                this.currentChar.beginTurn();
            }
        }else{
            return ;
        }
        if(this.currentChar.isAIControlled()){
            if(this.currentChar.getAI()!=null) {
                if(!this.currentChar.isDead()) {
                    this.currentChar.getAI().FullTurnBehavior();
                }
            }
            this.endTurn();//do we want this ?
        }
    }

    public void endTurn(){
        this.units.remove();
        if(this.units.size()==0){
            this.rebuildUnitQueue();
        }
        this.startNextTurn();
    }

    public ArrayList<Unit>getEnemies(){
        return this.enemies;
    }

    public ArrayList<Unit>getAllies(){
        return this.allies;
    }

    public PriorityQueue<Unit> getUnits(){
        return this.units;
    }
}
