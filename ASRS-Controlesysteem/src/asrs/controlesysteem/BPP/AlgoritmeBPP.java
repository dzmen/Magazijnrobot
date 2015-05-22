/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asrs.controlesysteem.BPP;

import asrs.controlesysteem.bestelling.Artikel;
import asrs.controlesysteem.bestelling.Order;
import java.util.ArrayList;

/**
 *
 * @author Hugo
 */
public final class AlgoritmeBPP {

    private final ArrayList<Artikel> doos1 = new ArrayList<>();
    private final ArrayList<Artikel> doos2 = new ArrayList<>();
    private final Order order;

    public AlgoritmeBPP(Order order) {
        this.order = order;
    }

    public void runAlgorithm() {
        ArrayList<Artikel> pakketten = new ArrayList<>(order.getRoute());
        ArrayList<Artikel> fitable = new ArrayList<>();
        ArrayList<Artikel> nonfitable = new ArrayList<>();
        ArrayList<Artikel> newArray = new ArrayList<>();
        double content = 0;
        while (pakketten.size() > 0) {
            for (Artikel aa : pakketten) {
                if (aa.getGrote() + content > 1) {
                    nonfitable.add(aa);
                } else {
                    fitable.add(aa);
                }
            }
            if (fitable.size() > 0) {
                double currentValue = 0;
                int currentIndex = 0;
                for (int ab = 0; ab < fitable.size(); ab++) {
                    if (fitable.get(ab).getGrote() > currentValue) {
                        currentValue = fitable.get(ab).getGrote();
                        currentIndex = ab;
                    }
                }
                newArray.add(fitable.get(currentIndex));
                content = content + fitable.get(currentIndex).getGrote();
                fitable.remove(currentIndex);
                pakketten.clear();
                for (Artikel ac : fitable) {
                    pakketten.add(ac);
                }
                for (Artikel af : nonfitable) {
                    pakketten.add(af);
                }
                fitable.clear();
                nonfitable.clear();

            } else {
                content = 0;
                nonfitable.clear();
            }
        }
        this.fillboxes(newArray);
    }

    public void fillboxes(ArrayList<Artikel> pakketten) {
        ArrayList<Artikel> doos = doos1;
        double doosinhoud = 0;
        for (Artikel ab : pakketten) {
            if (ab.getGrote() + doosinhoud > 1) {
                doos = doos2;
                doosinhoud = 0;
            }
            doos.add(ab);
            doosinhoud = doosinhoud + ab.getGrote();
        }
    }

    public ArrayList<Artikel> getDoos1() {
        return doos1;
    }

    public ArrayList<Artikel> getDoos2() {
        return doos2;
    }

}
