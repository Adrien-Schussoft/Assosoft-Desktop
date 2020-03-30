package com.assosoft.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler.LegendPosition;

import com.assosoft.dao.DonDAO;
import com.assosoft.model.Don;
import com.assosoft.utils.Tools;

public class RootStatView extends JPanel {

	private JPanel paneHeader = new JPanel();
	private JPanel paneContent = new JPanel();
	private JPanel paneFooter = new JPanel();

	private JLabel lblLeft = new JLabel();
	private JLabel lblRight = new JLabel();
	ImageIcon left = Tools.createImageIcon("/img/arrow-left.png");
	ImageIcon right = Tools.createImageIcon("/img/arrow-right.png");
	ArrayList<Don> listDon;

	public RootStatView() throws InvocationTargetException, InterruptedException {
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

		paneHeader.setBorder(border);
		paneContent.setBorder(border);
		paneFooter.setBorder(border);

		setLayout(new BorderLayout());

		/**
		 * Pane Content
		 */

		// Chart 1

		double[] xData = new double[] { 0.0, 1.0, 2.0 };
		double[] yData = new double[] { 0.0, 1.0, 2.0 };

		// Create Chart
		XYChart chart1 = QuickChart.getChart("Courbe du chemin vers le merveilleux", "X", "Y", "y(x) - JAVA", xData, yData);

		XChartPanel<Chart> chartPanel1 = new XChartPanel<Chart>(chart1);

		// Chart 2

		// Create Chart
		PieChart chart2 = new PieChartBuilder().width(800).height(600).title(getClass().getSimpleName()).build();

		// Customize Chart
		Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110),
				new Color(243, 180, 159), new Color(246, 199, 182) };
		chart2.getStyler().setSeriesColors(sliceColors);

		// Series
		chart2.addSeries("Gold", 24);
		chart2.addSeries("Silver", 21);
		chart2.addSeries("Platinum", 39);
		chart2.addSeries("Copper", 17);
		chart2.addSeries("Zinc", 40);

		XChartPanel<Chart> chartPanel2 = new XChartPanel<Chart>(chart2);

		// Chart Dons

//		JComboBox<String> yearCombobox = new JComboBox<String>();
		listDon = DonDAO.readAllDon();

		Map<String, List<Float>> map = new HashMap<>();

		int key = 2010;
		for (int i = 0; i <= 10; i++) {
			map.put(Integer.toString(key), new ArrayList<Float>());
			key++;
		}

		for (Don don : listDon) {
			map.get(don.getDate_Don().toString().substring(0, 4)).add(don.getMontant());
		}

		// creer une nouvelle collection avec une année et la somme des dons de cette
		// année
		Map<String, Float> result = new HashMap<>();

		for (Map.Entry<String, List<Float>> entry : map.entrySet()) {
			Float total = 0.0F;
			for (Float price : entry.getValue()) {
				total += price;
			}

			result.put(entry.getKey(), total);
		}

		// sort result by year with a TreeMap
		TreeMap<String, Float> resultSorted = new TreeMap<>();
		resultSorted.putAll(result);

		ArrayList<String> dates = new ArrayList<>();
		ArrayList<Float> prices = new ArrayList<>();

		for (String date : resultSorted.keySet()) {
			dates.add(date);
		}

		for (Float price : resultSorted.values()) {
			prices.add(price);
		}

		// Create Chart
		CategoryChart chart4 = new CategoryChartBuilder().width(800).height(600).title("Somme des dons par anneé")
				.xAxisTitle("Score").yAxisTitle("Montant").build();

		// Customize chart4
		chart4.getStyler().setLegendPosition(LegendPosition.InsideNW);
		chart4.getStyler().setHasAnnotations(true);

		// Series
		chart4.addSeries("Dons récolté par mois", dates, prices);

		XChartPanel<Chart> chartPanel4 = new XChartPanel<Chart>(chart4);

		CardLayout card = new CardLayout(20, 20);
		paneContent.setLayout(card);
		
		paneContent.add(chartPanel4);
		paneContent.add(chartPanel2);
		paneContent.add(chartPanel1);

		/**
		 * Pane Footer
		 */

		lblLeft.setIcon(left);
		lblRight.setIcon(right);

		lblLeft.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				card.previous(paneContent);
			}
		});

		lblRight.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				card.next(paneContent);
			}
		});

		paneFooter.add(lblLeft);
		paneFooter.add(lblRight);

		add(paneHeader, BorderLayout.NORTH);
		add(paneContent, BorderLayout.CENTER);
		add(paneFooter, BorderLayout.SOUTH);

	}

}
