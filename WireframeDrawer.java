/*
Delegated by repaint() on a JPanel. This will draw a Wireframe instance onto a Graphics2D object.

Anti-aliasing will be applied according to the setting of the boolean parameter
 */
package nz.ac.massey.a2;

import java.awt.*;

public class WireframeDrawer {

    public static void draw(Graphics2D g2, Wireframe wd, boolean antiAlias, int scale) {
        if (antiAlias) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        } else {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }

        for(int[] triangle: wd.triDrawable){
            Polygon aTriangle = new Polygon(new int[]{(int) (wd.transVertices[triangle[0]][0] * scale), (int) (wd.transVertices[triangle[1]][0] * scale), (int) (wd.transVertices[triangle[2]][0] * scale)},
                                            new int[]{(int) (wd.transVertices[triangle[0]][1] * scale), (int) (wd.transVertices[triangle[1]][1] * scale), (int) (wd.transVertices[triangle[2]][1] * scale)}, 3);
            g2.setColor(Color.gray);
            g2.fill(aTriangle);
            g2.setColor(Color.black);
            g2.draw(aTriangle);
        }




    }



}