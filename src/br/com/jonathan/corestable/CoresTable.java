/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jonathan.corestable;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ujcroli
 */
public class CoresTable extends JPanel {

    public CoresTable() {
        Object[] nomeColunas = {"Nome do Cliente", "Idade", "Email", "Ativo"};
        Object[][] dados = {
            {"Priciele Fragoso","20","priciele@gmmail.com",Boolean.TRUE},
            {"Jonathan","29","jonathan@gmail.com",Boolean.FALSE},
            {"Carlos","30","carlos@gmail.com",Boolean.TRUE},
            {"Kako","26","kako@gmail.com",Boolean.TRUE},
            {"Guilherme","27","gui@gmail.com",Boolean.FALSE}
        };
        DefaultTableModel defaultTableModel = new DefaultTableModel(dados, nomeColunas){
          @Override
          public Class getColumnClass(int column) {
              return getValueAt(0, column).getClass();
          }
        };
        
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("Cores Alternadas", CoresAlternadas(defaultTableModel));
        
        add(jTabbedPane);
        
    }

    private JComponent CoresAlternadas (DefaultTableModel defaultTableModel){
        JTable jTable = new JTable(defaultTableModel) {
            @Override
            public Component prepareRenderer (TableCellRenderer renderer, int row, int column){
                Component component = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    component.setBackground(row % 2 == 0 ? Color.ORANGE: Color.WHITE);
                }
                return component;
            }
        };
        jTable.setAutoCreateRowSorter(true);
        //jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
        jTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        return new JScrollPane(jTable);
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Teste");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(new CoresTable());
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
