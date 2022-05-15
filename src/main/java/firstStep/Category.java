package firstStep;

public enum Category {


    TRAVEL(1),
    CLOTHING(2),
    TRANSPORTATION(3),
    KITCHEN(4),
    GARDEN(5),
    SPORT(6),
    CAR(7),
    COMPUTER(8),
    CONSTRUCTION(9),
    FOOD(10);

    int categoryId;

    Category (int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId(){
    return this.categoryId;
    }


}

