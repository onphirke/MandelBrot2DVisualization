

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ChaosTheoryPanel extends JPanel implements Runnable {

	static final int WIDTH = 1000, HEIGHT = 1000, POINTSIZE = 2;
	private double startX = 2.75, maxX = 4, maxY = 1;
	private double r = startX, v = 0.01;

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		double[] ds = getValsAtR(r, v);
		for (int i = 0; i < ds.length; i++) {
			drawPoint(g2d, scaleX(r), scaleY(ds[i]));
		}
	}

	private double[] getValsAtR(double r, double val) {
		int times = (int) (2 / val);
		ArrayList<Double> vals = new ArrayList<>();
		for (int i = 0; i < times; i++) {
			vals.add(val = getNextVal(r, val));
		}

		ArrayList<Double> finalVals = new ArrayList<>();
		double precision = 0.0000000001;
		for (int i = vals.size() - 1; i > times / 2; i--) {
			if (containsAboutSame(vals, vals.get(i), precision)) {
				if (!containsAboutSame(finalVals, vals.get(i), precision)) {
					finalVals.add(vals.get(i));
				}
			}
		}

		double[] fv = new double[finalVals.size()];
		for (int i = 0; i < fv.length; i++) {
			fv[i] = finalVals.get(i);
		}
		return fv;
	}

	private boolean containsAboutSame(ArrayList<Double> ds, double d, double precision) {
		for (int i = 0; i < ds.size(); i++) {
			if (isAboutSame(ds.get(i), d, precision)) {
				return true;
			}
		}
		return false;
	}

	private boolean isAboutSame(double a, double b, double precision) {
		long mul = (long) (1 / precision);
		return ((long) (a * mul)) == ((long) (b * mul));
	}

	private int scaleX(double nsc) {
		return (int) (((nsc - startX) / maxX) * WIDTH * (((double)maxX) / (maxX-startX)));
	}

	private int scaleY(double nsc) {
		return (int) ((nsc / maxY) * HEIGHT);
	}

	private void drawPoint(Graphics2D g2d, int x, int y) {
		g2d.fillRect(x, HEIGHT - y, POINTSIZE, POINTSIZE);
	}

	private double getNextVal(double r, double val) {
		val = r * val * (1 - val);
		return val;
	}

	@Override
	public void run() {
		while (r < maxX) {
			repaint();
			r += 0.001;
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}