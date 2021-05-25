import jdistlib.F;
import jdistlib.T;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GUI {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel labelN;
    private static JLabel labelK;
    private static JButton button;
    private static JTextField fieldN;
    private static JTextField fieldK;
    private static JLabel meaningOfN;
    private static JLabel meaningOfK;
    private static JLabel warning;
    private static JLabel warning2;

    private static JTable table;
    private static JScrollPane scrollPane;

    private static JButton buttonRacunaj;
    private static JLabel warning3;

    private static JLabel ssaLbl;
    private static JLabel sseLbl;
    private static JLabel sstLbl;
    private static JLabel dfSsaLbl;
    private static JLabel dfSseLbl;
    private static JLabel dfSstLbl;
    private static JLabel ssaVarLbl;
    private static JLabel sseVarLbl;
    private static JLabel fCalculatedLbl;
    private static Choice choice;
    private static JLabel choiceLbl;
    private static Double alfa = 0.0;
    private static Double alfa2 = 0.0;
    private static Double ft = 0.0;
    private static Double dfSse = 0.0;
    private static Double dfSsa = 0.0;
    private static JLabel fTable;
    private static JLabel razlikaLbl;
    private static Double fIzracunato = 0.0;
    private static JLabel kontrasti;
    private static JLabel lblA1;
    private static JLabel lblA2;

    private static Choice choiceA1;
    private static Choice choiceA2;
    private static Choice choice2;
    private static JLabel greska;
    private static JLabel choice2Lbl;

    private static Double[] efekti;
    private static Double varSse = 0.0;
    private static JButton kontrastiButton;
    private static Double c1;
    private static Double c2;

    private static JLabel c1lbl;
    private static JLabel c2lbl;
    private static JTextField c1field;
    private static JTextField c2field;
    private static JLabel razlikaCova;


    public class HelpMethods {
        public boolean checkIsNumber(String s) {

            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher(s);

            if (s.contentEquals("1"))
                return false;
            if (matcher.matches())
                return true;
            else
                return false;
        }

        public boolean provjeriVrijednosti(TableModel model)
        {
            int n = model.getRowCount();
            int m = model.getColumnCount()-1;

            for(int i = 0; i < n; i++)
            {
                for(int j = 1; j <= m; j++)
                {
                    if( model.getValueAt(i, j) == null)
                        return false;
                }
            }
            return true;
        }
    }

    private Object[][] data(int brojMjerenja, int brojAlternativa) {
        Object[][] objects = new Object[brojMjerenja][brojAlternativa + 1];
        for (int i = 0; i < brojMjerenja; i++) {
            for (int j = 0; j < brojAlternativa + 1; j++) {
                if (j == 0)
                    objects[i][j] = i + 1;
                else
                    objects[i][j] = null;
            }
        }
        return objects;
    }

    private String[] columnNames(int brojAlternativa) {
        String[] names = new String[brojAlternativa + 1];
        names[0] = "BR. MJERENJA";
        for (int i = 1; i <= brojAlternativa; i++) {
            names[i] = "A " + i;
        }
        return names;
    }

    public GUI() {

        frame = new JFrame();

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.PINK);

        button = new JButton("Nacrtaj tabelu");
        button.setBounds(10, 80, 150, 25);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                actionOnFirstButton();
            }
        });

        panel.add(button);

        labelN = new JLabel("N");
        labelN.setBounds(10, 20, 80, 25);
        panel.add(labelN);
        fieldN = new JTextField();
        fieldN.setBounds(25, 20, 40, 25);
        fieldN.setHorizontalAlignment(JTextField.CENTER);
        panel.add(fieldN);
        meaningOfN = new JLabel("(broj mjeranja)");
        meaningOfN.setBounds(68, 20, 150, 25);
        panel.add(meaningOfN);

        labelK = new JLabel("K");
        labelK.setBounds(10, 50, 80, 25);
        panel.add(labelK);
        fieldK = new JTextField();
        fieldK.setBounds(25, 50, 40, 25);
        fieldK.setHorizontalAlignment(JTextField.CENTER);
        panel.add(fieldK);
        meaningOfK = new JLabel("(broj alternativa)");
        meaningOfK.setBounds(68, 50, 150, 25);
        panel.add(meaningOfK);

        warning = new JLabel("Prazno polje!");
        warning.setBounds(180, 80, 200, 25);
        panel.add(warning);
        warning.setVisible(false);

        warning2 = new JLabel("Pogrešan unos!");
        warning2.setBounds(180, 80, 200, 25);
        panel.add(warning2);
        warning2.setVisible(false);

        buttonRacunaj = new JButton("Izracunaj");
        buttonRacunaj.setBounds(270, 150, 150, 25);
        panel.add(buttonRacunaj);
        buttonRacunaj.setVisible(false);

        warning3 = new JLabel("Prazna polja!");
        warning3.setBounds(430, 150, 200, 25);
        panel.add(warning3);
        warning3.setVisible(false);

        ssaLbl = new JLabel("");
        ssaLbl.setBounds(50, 200, 200, 25);
        panel.add(ssaLbl);
        ssaLbl.setVisible(false);

        dfSsaLbl = new JLabel("");
        dfSsaLbl.setBounds(50, 230, 200, 25);
        panel.add(dfSsaLbl);
        dfSsaLbl.setVisible(false);

        ssaVarLbl = new JLabel("");
        ssaVarLbl.setBounds(50, 260, 200, 25);
        panel.add(ssaVarLbl);
        ssaVarLbl.setVisible(false);

        sseLbl = new JLabel("");
        sseLbl.setBounds(150, 200, 200, 25);
        panel.add(sseLbl);
        sseLbl.setVisible(false);

        dfSseLbl = new JLabel("");
        dfSseLbl.setBounds(150, 230, 200, 25);
        panel.add(dfSseLbl);
        dfSseLbl.setVisible(false);

        sseVarLbl = new JLabel("");
        sseVarLbl.setBounds(150, 260,200,25);
        panel.add(sseVarLbl);
        sseVarLbl.setVisible(false);

        sstLbl = new JLabel("");
        sstLbl.setBounds(250, 200, 200, 25);
        panel.add(sstLbl);
        sstLbl.setVisible(false);

        dfSstLbl = new JLabel("");
        dfSstLbl.setBounds(250, 230, 200, 25);
        panel.add(dfSstLbl);
        dfSstLbl.setVisible(false);

        fCalculatedLbl = new JLabel("");
        fCalculatedLbl.setBounds(50, 300, 200, 25);
        panel.add(fCalculatedLbl);
        fCalculatedLbl.setVisible(false);

        fTable = new JLabel("");
        fTable.setBounds(50, 330, 200, 25);
        panel.add(fTable);
        fTable.setVisible(false);

        razlikaLbl = new JLabel("");
        razlikaLbl.setBounds(225, 330, 200, 25);
        razlikaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(razlikaLbl);
        razlikaLbl.setVisible(false);

        choice = new Choice();
        choice.setBounds(250, 303, 80, 25);
        choice.add("");
        //choice.add("0.2");
        choice.add("0.1");
        choice.add("0.05");
        choice.add("0.01");
        panel.add(choice);
        choice.setVisible(false);

        choice2 = new Choice();
        choice2.setBounds(70, 483, 100, 25);
        choice2.add("");
        choice2.add("0.3");
        choice2.add("0.2");
        choice2.add("0.1");
        choice2.add("0.05");
        choice2.add("0.025");
        choice2.add("0.005");
        choice2.add("0.0005");
        panel.add(choice2);
        choice2.setVisible(false);

        greska = new JLabel("Alternative moraju biti različite!");
        greska.setBounds(50, 480, 200, 25);
        panel.add(greska);
        greska.setVisible(false);

        choice2Lbl = new JLabel("alfa:");
        choice2Lbl.setBounds(43, 480, 30, 25);
        panel.add(choice2Lbl);
        choice2Lbl.setVisible(false);

        choiceLbl = new JLabel("alfa:");
        choiceLbl.setBounds(225, 300, 80, 25);
        panel.add(choiceLbl);
        choiceLbl.setVisible(false);

        kontrasti = new JLabel("KONTRASTI (izaberite dvije alternative):");
        kontrasti.setBounds(10, 380, 300, 25);
        kontrasti.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(kontrasti);
        kontrasti.setVisible(false);

        lblA1 = new JLabel("A1:");
        lblA1.setBounds(50, 420, 20, 25);
        panel.add(lblA1);
        lblA1.setVisible(false);

        lblA2 = new JLabel("A2:");
        lblA2.setBounds(50, 450, 20, 25);
        panel.add(lblA2);
        lblA2.setVisible(false);

        choiceA1 = new Choice();
        choiceA1.setBounds(70, 423, 100, 25);
        choiceA1.add("");
        panel.add(choiceA1);
        choiceA1.setVisible(false);

        choiceA2 = new Choice();
        choiceA2.setBounds(70, 453, 100, 25);
        choiceA2.add("");
        panel.add(choiceA2);
        choiceA2.setVisible(false);

        kontrastiButton = new JButton("Izračunaj");
        kontrastiButton.setBounds(70, 513, 100, 25);
        panel.add(kontrastiButton);
        kontrastiButton.setVisible(false);

        c1lbl = new JLabel("C1:");
        c1lbl.setBounds(225, 420, 30, 25);
        panel.add(c1lbl);
        c1lbl.setVisible(false);

        c1field = new JTextField();
        c1field.setBounds(252, 422, 60, 25);
        panel.add(c1field);
        c1field.setVisible(false);

        c2lbl = new JLabel("C2:");
        c2lbl.setBounds(225, 450, 30, 25);
        panel.add(c2lbl);
        c2lbl.setVisible(false);

        c2field = new JTextField();
        c2field.setBounds(252, 452, 60, 25);
        panel.add(c2field);
        c2field.setVisible(false);

        razlikaCova = new JLabel();
        razlikaCova.setBounds(320, 420, 300, 25);
        razlikaCova.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(razlikaCova);
        razlikaCova.setVisible(false);

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("ANOVA I KONTRASTI");
        frame.setVisible(true);

        frame.add(panel);
    }

    public static void main(String[] args) {

        new GUI();

    }


  public void actionOnFirstButton() {

        HelpMethods help = new HelpMethods();
        String nString = fieldN.getText();
        String kString = fieldK.getText();

        if (nString.equals("") || kString.equals("")) {
            warning2.setVisible(false);
            warning.setVisible(true);
        } else if (!help.checkIsNumber(nString) || !help.checkIsNumber(kString)) {
            warning.setVisible(false);
            warning2.setVisible(true);
        } else {
            warning.setVisible(false);
            warning2.setVisible(false);

            int n = Integer.parseInt(nString);
            int k = Integer.parseInt(kString);

            DefaultTableModel model = new DefaultTableModel(data(n, k), columnNames(k)) {
                public boolean isCellEditable(int row, int column) {
                    return (column == 0) ? false : true;
                }
                public Class getColumnClass (int column) {
                    if(column == 0)
                        return Integer.class;
                    else
                        return Double.class;
                }
            };

            table = new JTable(model);
            table.setPreferredScrollableViewportSize(new Dimension(50, 10));
            table.setFillsViewportHeight(true);
            table.setGridColor(Color.PINK);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Double.class, centerRenderer);

            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(270, 20, 300, 100);
            panel.add(scrollPane);

            button.setVisible(false);
            buttonRacunaj.setVisible(true);

            buttonRacunaj.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    if(table.isEditing())
                        table.getCellEditor().stopCellEditing();
                    actionOnSecondButton();
                }
            });
        }
    }

    public void actionOnSecondButton() {

        HelpMethods help = new HelpMethods();

        Double[] doubleValue = new Double[table.getModel().getRowCount()];
        Double sum = 0.0;
        Double[] srednjaVrijednost = new Double[table.getModel().getColumnCount()-1];
        Double sum2 = 0.0;
        Double srednjaVrijednostSrednjihVrijednosti;
        efekti = new Double[table.getModel().getColumnCount() - 1];
        int brKolona = table.getModel().getColumnCount() - 1;
        Double sseV = 0.0;
        Double ssaV = 0.0;
        Double sstV = 0.0;

        Double dfSst = 0.0;
        Double varSsa = 0.0;
        Double varSst = 0.0;

        if(!help.provjeriVrijednosti(table.getModel())) {
            System.out.println("Prazna polja.");
            warning3.setVisible(true);
        }
        else {
            warning3.setVisible(false);

            for (int j = 1; j < table.getModel().getColumnCount(); j++) {
                sum = 0.0;
                for (int i = 0; i < table.getModel().getRowCount(); i++) {
                    doubleValue[i] = Double.parseDouble(table.getModel().getValueAt(i, j).toString());
                    sum += doubleValue[i];
                }
                srednjaVrijednost[j - 1] = sum / table.getModel().getRowCount();
                sum2 += srednjaVrijednost[j - 1];
            }

            srednjaVrijednostSrednjihVrijednosti = sum2 / brKolona;

            for (int i = 0; i < srednjaVrijednost.length; i++) {
                efekti[i] = srednjaVrijednost[i] - srednjaVrijednostSrednjihVrijednosti;
            }

            DefaultTableModel model1 = (DefaultTableModel)table.getModel();
            Object[] objects = new Object[table.getModel().getColumnCount()];
            objects[0] = "Srednja vrijednost";
            for(int i = 1; i < table.getModel().getColumnCount(); i++) {
                objects[i] = String.format("%.4f", srednjaVrijednost[i - 1]);
            }
            model1.addRow(objects);

            objects[0] = "Efekti";
            for(int i = 1; i < table.getModel().getColumnCount(); i++) {
                objects[i] = String.format("%.4f", efekti[i - 1]);
            }
            model1.addRow(objects);

            for(int j = 1; j < table.getModel().getColumnCount(); j++) {
                for(int i = 0; i < table.getModel().getRowCount() - 2; i++) {
                    Double temp = (Double)Double.parseDouble(table.getModel().getValueAt(i, j).toString()) - srednjaVrijednost[j-1];
                    temp *= temp;
                    sseV += temp;
                }
            }

            dfSse = (((table.getModel().getColumnCount() - 1)*(table.getModel().getRowCount()-3)) * 1.0);

            sseLbl.setText("SSE = " + String.format("%.4f", sseV));
            sseLbl.setVisible(true);
            dfSseLbl.setText("df(SSE) = " + dfSse);
            dfSseLbl.setVisible(true);

            for(int j = 1; j < table.getModel().getColumnCount(); j++) {
                ssaV += (efekti[j -1] * efekti[j - 1]);
            }

            ssaV *= table.getModel().getRowCount() - 2;
            dfSsa = (table.getModel().getColumnCount() - 2) * 1.0;

            ssaLbl.setText("SSA = " + String.format("%.4f", ssaV));
            ssaLbl.setVisible(true);
            dfSsaLbl.setText("df(SSE) = " + dfSsa);
            dfSsaLbl.setVisible(true);

            sstV = ssaV + sseV;
            dfSst = ((table.getModel().getColumnCount()-1)*(table.getModel().getRowCount()-2) - 1) * 1.0;

            sstLbl.setText("SST = " + String.format("%.4f", sstV));
            sstLbl.setVisible(true);
            dfSstLbl.setText("df(SST) = " + dfSst);
            dfSstLbl.setVisible(true);

            varSse = sseV/dfSse;
            sseVarLbl.setText("Se^2 = " + String.format("%.4f" , varSse));
            sseVarLbl.setVisible(true);
            varSsa = ssaV/dfSsa;
            ssaVarLbl.setText("Sa^2 = " + String.format("%.4f", varSsa));
            ssaVarLbl.setVisible(true);
            fIzracunato = varSsa/varSse;
            fCalculatedLbl.setText("Izračunato F = " + String.format("%.4f", fIzracunato));
            fCalculatedLbl.setVisible(true);

            choice.setVisible(true);
            choiceLbl.setVisible(true);

            choice.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent arg0) {
                    if(choice.getSelectedItem() != "") {
                        alfa = Double.parseDouble(choice.getSelectedItem());
                        ft = F.quantile(alfa, dfSsa, dfSse, false, false);
                        fTable.setText("Tabelarno F = " + String.format("%.4f", ft));
                        fTable.setVisible(true);
                        buttonRacunaj.setVisible(false);
                        choiceA1.removeAll();
                        choiceA2.removeAll();

                        if (fIzracunato > ft) {
                            razlikaLbl.setText("Sistemi se razlikuju!");
                            razlikaLbl.setVisible(true);
                        } else {
                            razlikaLbl.setText("Sistemi se ne razlikuju!");
                            razlikaLbl.setVisible(true);
                        }

                        kontrasti.setVisible(true);

                        for (int i = 1; i < table.getModel().getColumnCount(); i++) {
                            choiceA1.add(String.valueOf(i));
                            choiceA2.add(String.valueOf(i));
                        }

                        lblA1.setVisible(true);
                        lblA2.setVisible(true);
                        choiceA1.setVisible(true);
                        choiceA2.setVisible(true);

                        choiceA1.addItemListener(new ItemListener() {
                            public void itemStateChanged(ItemEvent arg0) {
                                if (!choiceA1.getSelectedItem().equals(choiceA2.getSelectedItem())) {
                                    if (choiceA1.getSelectedItem() != "") {
                                        greska.setVisible(false);
                                        int positionOfAlfa1 = Integer.valueOf(choiceA1.getSelectedItem()) - 1;
                                    }
                                }
                                else {
                                    greska.setVisible(true);
                                    System.out.println("Alternative moraju biti različite!");
                                }
                            }
                        });

                        choiceA2.addItemListener(new ItemListener() {
                            public void itemStateChanged(ItemEvent e) {
                                if (!choiceA2.getSelectedItem().equals(choiceA1.getSelectedItem())) {
                                    if (choiceA2.getSelectedItem() != "") {
                                        greska.setVisible(false);
                                        int positionOfAlfa2 = Integer.valueOf(choiceA2.getSelectedItem()) - 1;
                                        choice2.setVisible(true);
                                        choice2Lbl.setVisible(true);
                                    }
                                }
                                else {
                                    greska.setVisible(true);
                                    System.out.println("Alternative moraju biti različite!");
                                }
                            }
                        });

                        choice2.addItemListener(new ItemListener() {
                            public void itemStateChanged(ItemEvent e2) {
                                if (choice2.getSelectedItem() != "") {
                                    alfa2 = Double.parseDouble(choice2.getSelectedItem());
                                }

                                kontrastiButton.setVisible(true);
                                kontrastiButton.addMouseListener(new MouseAdapter() {

                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        actionOnThirdButton();
                                    }
                                });
                            }
                        });
                    }
                }
            });
        }
    }

    public void actionOnThirdButton() {

        Double alternativa1 = 0.0;
        Double alternativa2 = 0.0;

        for(int i = 0; i < table.getModel().getColumnCount(); i++) {
            if(choiceA1.getSelectedItem() != "" || choiceA2.getSelectedItem() != "") {
                if (i == Double.valueOf(choiceA1.getSelectedItem()))
                    alternativa1 = efekti[i - 1];
                else if (i == Double.valueOf(choiceA2.getSelectedItem()))
                    alternativa2 = efekti[i - 1];
            }
        }

        Double c = Math.round((alternativa1 - alternativa2) * 100000d)/100000d;
        Double Sc = Math.round((Math.sqrt(varSse) * Math.sqrt(2.0/(((table.getModel().getColumnCount() - 1)) * (table.getModel().getRowCount() - 2)))) * 100000d) / 100000d;
        Double t = Math.round(T.quantile(alfa2/2, dfSse, false, false) * 100000d) / 100000d;
        System.out.println("T je:" + t);

        c1 = c - t*Sc;
        c1lbl.setVisible(true);
        c1field.setVisible(true);
        c1field.setText(String.format("%.4f", c1));

        c2 = c + t*Sc;
        c2lbl.setVisible(true);
        c2field.setVisible(true);
        c2field.setText(String.format("%.4f", c2));

        if(c1 <= 0 && c2 >= 0)
            razlikaCova.setText("Ne postoji značajna statistička razlika!");
        else
            razlikaCova.setText("Postoji značajna statistička razlika!");
        razlikaCova.setVisible(true);
    }
  }