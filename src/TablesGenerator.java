public class TablesGenerator {

    public static int biomsFoodTabSize = 0;
    public static int biomsPredatorTabSize = 0;

    private static int biomFNr1, biomFNr2, biomFNr3, biomFNr4;
    private static int biomPNr1, biomPNr2, biomPNr3, biomPNr4;

    public static void checkTabSize(){

        switch(BiomDeployer.getAreaNr1()){
            case 1: biomsFoodTabSize+=5; biomsPredatorTabSize+=2; biomFNr1 = 5; biomPNr1 = 2; break;
            case 2: biomsFoodTabSize+=1; biomsPredatorTabSize+=1; biomFNr1 = 1; biomPNr1 = 1; break;
            case 3: biomsFoodTabSize+=4; biomsPredatorTabSize+=2; biomFNr1 = 4; biomPNr1 = 2; break;
            case 4: biomsFoodTabSize+=2; biomsPredatorTabSize+=2; biomFNr1 = 2; biomPNr1 = 2; break;
            case 5: biomsFoodTabSize+=3; biomsPredatorTabSize+=3; biomFNr1 = 3; biomPNr1 = 3; break;
        }
        switch(BiomDeployer.getAreaNr2()){
            case 1: biomsFoodTabSize+=5; biomsPredatorTabSize+=2; biomFNr1 = 5; biomPNr2 = 2; break;
            case 2: biomsFoodTabSize+=1; biomsPredatorTabSize+=1; biomFNr1 = 1; biomPNr2 = 1; break;
            case 3: biomsFoodTabSize+=4; biomsPredatorTabSize+=2; biomFNr1 = 4; biomPNr2 = 2; break;
            case 4: biomsFoodTabSize+=2; biomsPredatorTabSize+=2; biomFNr1 = 2; biomPNr2 = 2; break;
            case 5: biomsFoodTabSize+=3; biomsPredatorTabSize+=3; biomFNr1 = 3; biomPNr2 = 3; break;
        }
        switch(BiomDeployer.getAreaNr3()){
            case 1: biomsFoodTabSize+=5; biomsPredatorTabSize+=2; biomFNr1 = 5; biomPNr3 = 2; break;
            case 2: biomsFoodTabSize+=1; biomsPredatorTabSize+=1; biomFNr1 = 1; biomPNr3 = 1; break;
            case 3: biomsFoodTabSize+=4; biomsPredatorTabSize+=2; biomFNr1 = 4; biomPNr3 = 2; break;
            case 4: biomsFoodTabSize+=2; biomsPredatorTabSize+=2; biomFNr1 = 2; biomPNr3 = 2; break;
            case 5: biomsFoodTabSize+=3; biomsPredatorTabSize+=3; biomFNr1 = 3; biomPNr3 = 3; break;
        }
        switch(BiomDeployer.getAreaNr4()){
            case 1: biomsFoodTabSize+=5; biomsPredatorTabSize+=2; biomFNr1 = 5; biomPNr4 = 2; break;
            case 2: biomsFoodTabSize+=1; biomsPredatorTabSize+=1; biomFNr1 = 1; biomPNr4 = 1; break;
            case 3: biomsFoodTabSize+=4; biomsPredatorTabSize+=2; biomFNr1 = 4; biomPNr4 = 2; break;
            case 4: biomsFoodTabSize+=2; biomsPredatorTabSize+=2; biomFNr1 = 2; biomPNr4 = 2; break;
            case 5: biomsFoodTabSize+=3; biomsPredatorTabSize+=3; biomFNr1 = 3; biomPNr4 = 3; break;
        }

    }

    private static int foodTab[];
    private static int predatorTab[];
    private static int foodTabIt = 0;
    private static int predatorTabIt = 0;

    public static void createTabs(){

        foodTab = new int[biomsFoodTabSize];
        predatorTab = new int[biomsPredatorTabSize];

        while(biomFNr1 > 0){
            foodTab[foodTabIt] = 1;
            foodTabIt++;
            biomFNr1--;
        }
        while(biomPNr1 > 0){
            predatorTab[predatorTabIt] = 1;
            predatorTabIt++;
            biomPNr1--;
        }
        while(biomFNr2 > 0){
            foodTab[foodTabIt] = 2;
            foodTabIt++;
            biomFNr2--;
        }
        while(biomPNr2 > 0){
            predatorTab[predatorTabIt] = 2;
            predatorTabIt++;
            biomPNr2--;
        }
        while(biomFNr3 > 0){
            foodTab[foodTabIt] = 3;
            foodTabIt++;
            biomFNr3--;
        }
        while(biomPNr3 > 0){
            predatorTab[predatorTabIt] = 3;
            predatorTabIt++;
            biomPNr3--;
        }
        while(biomFNr4 > 0){
            foodTab[foodTabIt] = 4;
            biomFNr4--;
        }
        while(biomPNr4 > 0){
            predatorTab[predatorTabIt] = 4;
            biomPNr4--;
        }
    }

    public static int getBiomsFoodTabSize() {
        return biomsFoodTabSize;
    }

    public static int getBiomsPredatorTabSize() {
        return biomsPredatorTabSize;
    }

    public static int[] getFoodTab() { return foodTab; }

    public static int[] getPredatorTab() {
        return predatorTab;
    }
}
