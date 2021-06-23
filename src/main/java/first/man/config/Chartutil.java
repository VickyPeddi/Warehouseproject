package first.man.config;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class Chartutil {


    public void generatePiechart(String location, List<Object[]> list) {
        //create dataset and read list<object[]>values
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Object[] ob : list) {
            //key =Shipment mode ,value =no of Shipment mode
            dataset.setValue(ob[0].toString(), Integer.valueOf(ob[1].toString()));
        }

        //conver dataset values into jfree chart object using Chat Factory class
        JFreeChart pieChart3D = ChartFactory.createPieChart("Mode", dataset);
        //convert jfreechart object into one image using chartutils class
        try {

            ChartUtils.saveChartAsJPEG(new File(location + "/shipmentA.jpg"), pieChart3D, 800, 800);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void generateBarchart(String location, List<Object[]> list) {

        try {
            //create a dataset for barchart
            DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
            for (Object[] ob : list) {
                categoryDataset.setValue(Integer.valueOf(ob[1].toString()), ob[0].toString(), "");
            }
            //convert dataset values into jfreechart object
            JFreeChart barChart = ChartFactory.createBarChart("SHiPMENT MODE DETAILS", "Shipment Types", "Count", categoryDataset);
            //convert jfreechart into image by using chartutils
            ChartUtils.saveChartAsJPEG(new File(location+"/shipmentB.jpg"), barChart, 700, 500);



        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }
}
