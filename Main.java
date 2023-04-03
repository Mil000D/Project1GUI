import java.awt.*;
import java.util.Locale;

class Parachute extends Frame {
    public String str;
    public String binaryVal;

    public Parachute(String str, String binaryVal) {
        this.str = str;
        this.binaryVal = binaryVal;
    }

    public Parachute(String str) {
        this.str = str;
        this.setSize(500, 500);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        int x = 50;
        int y = 50;
        int r = 400;
        int i;
        int startangle = -360 / countingNumb(this.toArray());
        int arcangle = 360 / countingNumb(this.toArray());
        for (int k = splitting(this.toArray()).length - 1; k >= 0; k--) {
            for (i = 0; i < splitting(this.toArray())[k].length(); i++) {
                switch (splitting(this.toArray())[k].charAt(i)) {
                    case '0' -> g.setColor(Color.white);
                    case '1' -> g.setColor(Color.red);
                }
                g.fillArc(x, y, r, r, startangle, arcangle);
                startangle = startangle - 360 / countingNumb(this.toArray());
            }
            startangle = 0;
            arcangle = 360 - arcangle * i;
            g.setColor(Color.blue);
            g.fillArc(x, y, r, r, startangle, arcangle);


            g.setColor(Color.black);
            g.drawOval(x, y, r, r);
            r /= 2;
            x = x + r / 2;
            y = y + r / 2;
            startangle = -360 / countingNumb(this.toArray());
            arcangle = 360 / countingNumb(this.toArray());

        }
        g.drawLine(250, 250, 450, 250);
    }

    public String getStr() {
        return str;
    }

    public String getBinaryVal() {
        return binaryVal;
    }

    public String toBinary(int x) {
        StringBuilder bin = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            int y = x >> i;
            if ((y & 1) > 0) {
                bin.append("1");
            } else {
                bin.append("0");
            }
        }
        return bin.toString();
    }

    public Parachute[] toArray() {
        String s = "abcdefghijklmnopqrstuvwxyz ,./|[]{}()";
        Parachute[] p = new Parachute[str.length()];
        for (int i = 0, j = 0; j < s.length() && i < str.length(); i++, j++) {
            while (!str.substring(i, i + 1).equalsIgnoreCase(s.substring(j, j + 1))) {
                j++;
                if (j == s.length()) {
                    j = 0;
                    break;
                }
            }
            p[i] = new Parachute(s.substring(j, j + 1).toUpperCase(Locale.ROOT), toBinary(j + 1));
            j = 0;
        }
        return p;
    }

    public String[] splitting(Parachute[] p) {
        String[] ss = new String[countingSpaces(p) + 1];
        for (int j = 0, i = 0; i < ss.length; i++) {
            ss[i] = "";
            for (; j < p.length; j++) {
                if (p[j].getStr().equals(" ")) {
                    j++;
                    break;
                }
                ss[i] += p[j].getBinaryVal();
            }
        }
        return ss;
    }

    public int countingSpaces(Parachute[] p) {
        int count = 0;
        for (Parachute parachute : p) {
            if (parachute.getStr().equals(" ")) {
                count++;
            }
        }
        return count;
    }

    public int countingNumb(Parachute[] p) {
        String[] ss = splitting(p);
        int max = ss[0].length();
        for (int i = 1; i < ss.length; i++) {
            if (max < ss[i].length()) {
                max = ss[i].length();
            }
        }
        return max;
    }

    public void printArray(Parachute[] p) {
        for (Parachute parachute : p) {
            System.out.println(parachute);
        }
    }

    public String toString() {
        return str + " -> " + binaryVal;
    }
}

public class Main {
    public static void main(String[] args) {
        Parachute p = new Parachute("i like pizza");
        p.printArray(p.toArray());
    }
}