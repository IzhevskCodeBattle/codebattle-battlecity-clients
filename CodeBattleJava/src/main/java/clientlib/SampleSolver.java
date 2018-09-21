package clientlib;

import java.util.*;
import static clientlib.Elements.*;


public class SampleSolver extends Solver {


    public List<Point> getPlayerTankCoordinates(){
            List<Point> playerTank = getCoordinates(TANK_DOWN, TANK_UP, TANK_LEFT, TANK_RIGHT);
            return playerTank;
        }


    public List<Point> getOtherPlayersTanks(){
        List<Point> otherPlayers = getCoordinates(OTHER_TANK_DOWN, OTHER_TANK_UP, OTHER_TANK_LEFT, OTHER_TANK_RIGHT);
        return otherPlayers;
    }


    public List<Point> getBotsTanks(){
        List<Point> bots = getCoordinates(AI_TANK_DOWN, AI_TANK_UP, AI_TANK_LEFT, AI_TANK_RIGHT);
        return bots;
    }

    public List<Point> getBullets(){
        List<Point> bullets = getCoordinates(BULLET);
        return bullets;
    }

    public List<Point> getWormholes(){
        List<Point> wormholes = getCoordinates(WORM_HOLE);
        return wormholes;
    }

    public List<Point> getConstructions(){
        List<Point> constructions = getCoordinates(CONSTRUCTION);
        return constructions;
    }

    public List<Point> getDestroyedConstructions(){
        List<Point> constructions = getCoordinates(CONSTRUCTION_DESTROYED_DOWN,
                CONSTRUCTION_DESTROYED_UP,
                CONSTRUCTION_DESTROYED_LEFT,
                CONSTRUCTION_DESTROYED_RIGHT,
                CONSTRUCTION_DESTROYED,

                CONSTRUCTION_DESTROYED_DOWN_TWICE,
                CONSTRUCTION_DESTROYED_UP_TWICE,
                CONSTRUCTION_DESTROYED_LEFT_TWICE,
                CONSTRUCTION_DESTROYED_RIGHT_TWICE,

                CONSTRUCTION_DESTROYED_LEFT_RIGHT,
                CONSTRUCTION_DESTROYED_UP_DOWN,

                CONSTRUCTION_DESTROYED_UP_LEFT,
                CONSTRUCTION_DESTROYED_RIGHT_UP,
                CONSTRUCTION_DESTROYED_DOWN_LEFT,
                CONSTRUCTION_DESTROYED_DOWN_RIGHT);
        return constructions;
    }

    public List<Point> getBogs(){
        List<Point> bogs = getCoordinates(BOG);
        return bogs;
    }

    public List<Point> getSands() {
        List<Point> sand = getCoordinates(SAND);
        return sand;
    }

    public List<Point> getMoats() {
        List<Point> moat = getCoordinates(MOAT_HORIZONTAL, MOAT_VERTICAL);
        return moat;
    }

    public List<Point> getHedgehogs() {
        List<Point> hedgehog = getCoordinates(HEDGEHOG);
        return hedgehog;
    }

    public List<Point> getWalls(){
        List<Point> walls = getCoordinates(BATTLE_WALL);
        return walls;
    }

    public List<Point> getBarriers(){
        List<Point> barriers = new ArrayList<>();
        barriers.addAll(getWalls());
        barriers.addAll(getConstructions());
        barriers.addAll(getDestroyedConstructions());
        barriers.addAll(getOtherPlayersTanks());
        barriers.addAll(getBotsTanks());
        barriers.addAll(getHedgehogs());
        return barriers;
    }

    public List<Point> getAmmoBonuses(){
        List<Point> ammo = getCoordinates(BONUS_AMMO);
        return ammo;
    }

    public List<Point> getMedKitBonuses(){
        List<Point> medkit = getCoordinates(MEDKIT);
        return medkit;
    }

    public boolean isNear(int x, int y, Elements el){
        boolean isNear = false;
        if(field[x+1][y] == el ||
                field[x-1][y] == el ||
                field[x][y-1] == el ||
                field[x][y+1] == el){
            isNear = true;
        }

        return isNear;
    }

    public boolean isBarrierAt(int x, int y){
        return getBarriers().contains(new Point(x,y));
    }

    public boolean isAt(int x, int y, Elements element){
        if(isOutOfBounds(x, y)){
            return false;
        } else{
            return field[x][y] == element;
        }
    }

    public int countNear(int x, int y, Elements element){
        int counter = 0;
        if(field[x+1][y] == element) counter++;
        if(field[x-1][y] == element) counter++;
        if(field[x][y+1] == element) counter++;
        if(field[x][y-1] == element) counter++;

        return counter;
    }

    public boolean isOutOfBounds(int x, int y){
        return x >= field.length || y >= field.length || x < 0 || y < 0;
    }



    public List<Point> getCoordinates(Elements... findElements){
        Set<Elements> findSetElements = new HashSet<>(Arrays.asList(findElements));
        List<Point> elementsCoordinates = new ArrayList<Point>();

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field.length; x++) {
                if (findSetElements.contains(field[x][y])) {
                    elementsCoordinates.add(new Point(x,y));
                }
            }
        }
        return elementsCoordinates;
    }



    @Override
    public String move() {

        getPlayerTankCoordinates();
        getOtherPlayersTanks();
        getBotsTanks();
        getBullets();
        getWormholes();
        getConstructions();
        getDestroyedConstructions();
        getBogs();
        getSands();
        getMoats();
        getHedgehogs();
        getWalls();
        getBarriers();
        getAmmoBonuses();
        getMedKitBonuses();
        isNear(14, 5, CONSTRUCTION);
        isBarrierAt(13, 14);
        isAt(0,0, BATTLE_WALL); // этот ли эл в этой точке
        countNear(14,5, CONSTRUCTION); // сколько таких элементов около этой точки

        return left();
    }
}
