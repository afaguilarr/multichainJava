package otherApp;

class Color {
    int startValue, endValue;
    java.awt.Color value;

    Color(int startValue, int endValue, int index) {
        this.startValue = startValue;
        this.endValue = endValue;
        this.value = getColor(index);
    }

    static java.awt.Color getColor(int number) {
        switch (number) {
            case 1:
                return java.awt.Color.red;
            case 2:
                return java.awt.Color.blue;
            case 3:
                return java.awt.Color.green;
            case 4:
                return java.awt.Color.yellow;
            case 5:
                return java.awt.Color.cyan;
            case 6:
                return java.awt.Color.pink;
            case 7:
                return java.awt.Color.orange;
            case 8:
                return java.awt.Color.magenta;
            case 9:
                return java.awt.Color.LIGHT_GRAY;
            case 10:
                return java.awt.Color.white;
            default:
                return null;
        }
    }
}
