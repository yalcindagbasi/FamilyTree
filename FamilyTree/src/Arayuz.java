


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Arayuz extends JFrame {
    static int screenWidth = 1920;
    static int screenHeight = 1080;

    static int panelWidth= 100;

    static int panelHeight=50;
    static JLabel[] label = new JLabel[200];
    static JPanel[] panel = new JPanel[200];
    static JPanel[] cizgiPanel=new JPanel[100];
    static ArrayList<Kisi> herkes2 = FamilyTree.herkes2;
    static JPanel soyAgaciPanel = new JPanel();
    JPanel menuPaneli= new JPanel();
    static JTextPane konsol= new JTextPane();
    JFrame anaEkran = new JFrame();

    public Arayuz() {

        menuPaneli.setLayout(null);
        soyAgaciPanel.setLayout(null);
        menuPaneli.setBounds(0,screenHeight-(screenHeight/4),screenWidth,screenHeight/4);
        menuPaneli.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        soyAgaciPanel.add(menuPaneli);

        konsol.setEditable(false);
        JScrollPane konsolscroll= new JScrollPane(konsol);
        konsolscroll.setBounds(screenWidth*2/3,screenHeight-(screenHeight/4)+10,screenWidth/3-20,screenHeight/4-50);
        konsolscroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        konsol.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        konsolscroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

       soyAgaciPanel.add(konsolscroll);
        konsol.setText("Çıktı Buradadır ve Kaydırılabilir.\n");

        anaEkran.getContentPane().add(soyAgaciPanel, BorderLayout.CENTER);

        agacYazdir(FamilyTree.root);
        cizgiCek(FamilyTree.root);
        butonlar();
        anaEkran.setBounds(0, 0, screenWidth, screenHeight);
        anaEkran.setResizable(false);
        anaEkran.setDefaultCloseOperation(EXIT_ON_CLOSE);
        anaEkran.setVisible(true);



    }


    public static void main() {
        Arayuz arayuz = new Arayuz();


    }

    public void butonlar(){

        JButton cocuguOlmayanlarButton= new JButton("Çocuğu Olmayan Düğümler");
        cocuguOlmayanlarButton.setBounds(20,20,200,25);
        cocuguOlmayanlarButton.setVisible(true);
        menuPaneli.add(cocuguOlmayanlarButton);
        cocuguOlmayanlarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renkKontrol(FamilyTree.root);
                FamilyTree.cocuguOlmayanDugumler(FamilyTree.root);
                FamilyTree.yazdirCocuguOlmayanDugumler();

                for (int j = 0; j < FamilyTree.cocuguOlmayandugumler.size(); j++) {

                    panel[FamilyTree.cocuguOlmayandugumler.get(j).labelindex].setBackground(Color.RED);
                }

            }
        });

        JButton kanGrubuButton= new JButton("Kan Grubu Olanları Göster");
        kanGrubuButton.setBounds(20,55,200,25);
        kanGrubuButton.setVisible(true);
        menuPaneli.add(kanGrubuButton);
        String[] kangruplari = {"0(+)","0(-)","A(+)","A(-)","B(+)","B(-)","AB(+)","AB(-)"};
        JComboBox kangruplariCombo= new JComboBox(kangruplari);
        kangruplariCombo.setBounds(230,55,50,25);
        kangruplariCombo.setVisible(true);
        menuPaneli.add(kangruplariCombo);
        kanGrubuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renkKontrol(FamilyTree.root);
                FamilyTree.kanGrubuOlanlariListele((String) kangruplariCombo.getSelectedItem());
            }
        });
        System.out.println("*******************************");
        JButton ismiAyniOlanlarButton= new JButton("İsmi Bul");
        ismiAyniOlanlarButton.setBounds(20,90,200,25);
        ismiAyniOlanlarButton.setVisible(true);
        menuPaneli.add(ismiAyniOlanlarButton);
        JTextField ismiAyniOlanlarText= new JTextField() ;
        ismiAyniOlanlarText.setBounds(230,90,200,25);
        ismiAyniOlanlarText.setVisible(true);
        menuPaneli.add(ismiAyniOlanlarText);
        ismiAyniOlanlarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renkKontrol(FamilyTree.root);
                FamilyTree.ismiAyniOlanlar(ismiAyniOlanlarText.getText());

            }
        });

        JButton meslegiDevamEttirenlerButton= new JButton("Atasının Mesleğini Devam Ettirenler");
        meslegiDevamEttirenlerButton.setBounds(20,125,200,25);
        meslegiDevamEttirenlerButton.setVisible(true);
        menuPaneli.add(meslegiDevamEttirenlerButton);
        meslegiDevamEttirenlerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renkKontrol(FamilyTree.root);
                FamilyTree.meslegiDevamEttirenler(FamilyTree.root);
            }
        });

        JButton akrabalikBulButton= new JButton("Akrabalık Bul");
        akrabalikBulButton.setBounds(450,20,200,25);
        akrabalikBulButton.setVisible(true);
        menuPaneli.add(akrabalikBulButton);
        JTextField akrabalikBul1= new JTextField() ;
        akrabalikBul1.setBounds(660,20,75,25);
        akrabalikBul1.setVisible(true);
        menuPaneli.add(akrabalikBul1);
        JTextField akrabalikBul2= new JTextField() ;
        akrabalikBul2.setBounds(735,20,75,25);
        akrabalikBul2.setVisible(true);
        menuPaneli.add(akrabalikBul2);
        akrabalikBulButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FamilyTree.akrabalikBul(akrabalikBul1.getText(),akrabalikBul2.getText());
            }
        });

        JButton kisinesilBilgisiGosterButton= new JButton("Kişi Sonrası Nesil");
        kisinesilBilgisiGosterButton.setBounds(450,55,200,25);
        kisinesilBilgisiGosterButton.setVisible(true);
        menuPaneli.add(kisinesilBilgisiGosterButton);
        JTextField kisinesilBilgisiGosterText= new JTextField("Emin Kaya");
        kisinesilBilgisiGosterText.setBounds(660,55,200,25);
        kisinesilBilgisiGosterText.setVisible(true);
        menuPaneli.add(kisinesilBilgisiGosterText);
        kisinesilBilgisiGosterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namesurname=kisinesilBilgisiGosterText.getText();
                String surname= namesurname.substring(namesurname.lastIndexOf(" ")+1,namesurname.length()) ;
                String name=namesurname.substring(0,namesurname.indexOf(" "));
                Kisi aranan= FamilyTree.search(FamilyTree.root,name,surname);
                konsol.setText( konsol.getText()+"\nKişiden Sonra Gelen Nesil Sayısı: "+ name +" "+  surname + " " + (FamilyTree.nesilBilgisiBul(aranan)-1) + "\n *************************\n");

            }
        })  ;
        JButton soyAgaciniGosterButton= new JButton("Ailesini Göster");
        soyAgaciniGosterButton.setBounds(450,90,200,25);
        soyAgaciniGosterButton.setVisible(true);
        menuPaneli.add(soyAgaciniGosterButton);
        JTextField soyAgaciniGosterText= new JTextField();
        soyAgaciniGosterText.setBounds(660,90,200,25);
        soyAgaciniGosterText.setVisible(true);
        menuPaneli.add(soyAgaciniGosterText);
        soyAgaciniGosterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renkKontrol(FamilyTree.root);
                String namesurname=soyAgaciniGosterText.getText();
                String surname= namesurname.substring(namesurname.lastIndexOf(" ")+1,namesurname.length()) ;
                String name=namesurname.substring(0,namesurname.indexOf(" "));
                Kisi aranan= FamilyTree.search(FamilyTree.root,name,surname);
                FamilyTree.ailesiniGoster(aranan);
                panel[FamilyTree.root.labelindex].setBackground(Color.GRAY);
            }
        });
        JButton nesilMaxGoster= new JButton("Max Nesil");
        nesilMaxGoster.setBounds(450,125,200,25);
        nesilMaxGoster.setVisible(true);
        menuPaneli.add(nesilMaxGoster);
        nesilMaxGoster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Arayuz.konsol.setText(Arayuz.konsol.getText()+"\n Toplam Nesil Sayısı: "+ FamilyTree.maxNesil());
            }
        });


    }

    static int index = 0;
    static int kacinci = 0;
    static int x=0;

    void renkKontrol(Kisi kisi){
        if(kisi.cinsiyet.equals(Cinsiyet.ERKEK)){
            panel[kisi.labelindex].setBackground(Color.GRAY);
            panel[kisi.esi.labelindex].setBackground(Color.PINK);
        }
        else {
            panel[kisi.labelindex].setBackground(Color.PINK);
            panel[kisi.esi.labelindex].setBackground(Color.GRAY);
        }
        if(kisi.cocuklari.size()!=0 || kisi.cocuklari!=null)
            for (int j = 0; j < kisi.cocuklari.size(); j++) {
                renkKontrol(kisi.cocuklari.get(j));
            }
    }
    void cizgiCek(Kisi kisi) {

        if(kisi.esi.adi!=null) {
            cizgiPanel[x] = new JPanel();
            cizgiPanel[x].setBounds(panel[kisi.labelindex].getX() + 100, panel[kisi.labelindex].getY() + 25, panel[kisi.esi.labelindex].getX()-panel[kisi.labelindex].getX(), 1);
            cizgiPanel[x].setBorder(BorderFactory.createLineBorder(Color.black));
            soyAgaciPanel.add(cizgiPanel[x]);
            x++;


        }
        if(kisi.cocuklari.size()!=0)
        {
            cizgiPanel[x] = new JPanel();
            cizgiPanel[x].setBounds(panel[kisi.labelindex].getX()+panelWidth + ((panel[kisi.esi.labelindex].getX()-(panel[kisi.labelindex].getX()+100))/2), panel[kisi.labelindex].getY() + 25, 1, 50);
            cizgiPanel[x].setBorder(BorderFactory.createLineBorder(Color.black));
            soyAgaciPanel.add(cizgiPanel[x]);
            x++;
            cizgiPanel[x]=new JPanel();
            cizgiPanel[x].setBounds(panel[kisi.cocuklari.get(0).labelindex].getX()+50,panel[kisi.labelindex].getY()+75,panel[kisi.cocuklari.get(kisi.cocuklari.size()-1).labelindex].getX()-panel[kisi.cocuklari.get(0).labelindex].getX(),1);
            cizgiPanel[x].setBorder(BorderFactory.createLineBorder(Color.black));
            soyAgaciPanel.add(cizgiPanel[x]);
            x++;
        }
        if(kisi.cocuklari!=null)
        for(int i=0;i<kisi.cocuklari.size();i++)
        {
            cizgiPanel[x]=new JPanel();
            cizgiPanel[x].setBounds(panel[kisi.cocuklari.get(i).labelindex].getX()+50,panel[kisi.labelindex].getY()+75,1,25);
            cizgiPanel[x].setBorder(BorderFactory.createLineBorder(Color.black));
            soyAgaciPanel.add(cizgiPanel[x]);
            x++;
        }


        if(kisi.cocuklari!=null)
            for(int i=0;i<kisi.cocuklari.size();i++){
                cizgiCek(kisi.cocuklari.get(i));
            }

    }

    public static void agacYazdir(Kisi kisi) {

        if (kisi == FamilyTree.root) {
            panel[index] = new JPanel();
            label[index] = new JLabel(kisi.toString());
            panel[index].setBounds((screenWidth / 2) - 100, 20 + (kisi.nesil * 100), 100, 50);
            panel[index].add(label[index]);
            label[index].setBounds(10, 10, 70, 70);
            kisi.xCoord = 800 / 2 - 100;
            kisi.xCoordSinirlarisol = 0;
            kisi.xCoordSinirlarisag = 1920;
            panel[index].setBackground(Color.GRAY);
            soyAgaciPanel.add(panel[index]);
            kisi.labelindex= index;
            index++;
            panel[index] = new JPanel();
            label[index] = new JLabel(kisi.esi.toString());
            panel[index].setBounds((screenWidth / 2) + 110, 20 + (kisi.esi.nesil * 100), 100, 50);
            panel[index].add(label[index]);
            label[index].setBounds(10, 10, 70, 70);
            panel[index].setBackground(Color.PINK);
            soyAgaciPanel.add(panel[index]);
            kisi.esi.labelindex= index;
            index++;

        } else if (kisi.babasi != null) {
            panel[index] = new JPanel();
            label[index] = new JLabel(kisi.toString());
            FamilyTree.ayniNesilSayisiBul(kisi.babasi, kisi.babasi.nesil);
            kisi.xCoordSinirlarisag = kisi.babasi.xCoordSinirlarisol + (kisi.babasi.siraCocuk * ((kisi.babasi.xCoordSinirlarisag - kisi.babasi.xCoordSinirlarisol) / kisi.babasi.cocuklari.size()));
            kisi.xCoordSinirlarisol = kisi.babasi.xCoordSinirlarisol + (kisi.babasi.siraCocuk - 1) * ((kisi.babasi.xCoordSinirlarisag - kisi.babasi.xCoordSinirlarisol) / kisi.babasi.cocuklari.size());
            kisi.xCoord = (kisi.xCoordSinirlarisag + kisi.xCoordSinirlarisol) / 2;


            panel[index].setBounds(kisi.xCoord, 20 + (kisi.nesil * 100), 100, 50);
            panel[index].add(label[index]);
            label[index].setBounds(10, 10, 70, 70);
            kisi.labelindex = index;
            kisi.siraKendi = kisi.babasi.siraCocuk;
            kisi.babasi.siraCocuk++;
            if (kisi.cinsiyet.equals(Cinsiyet.KIZ))
                panel[index].setBackground(Color.PINK);
            else
                panel[index].setBackground(Color.GRAY);

            soyAgaciPanel.add(panel[index]);
            index++;
        } else if (kisi.annesi != null) {
            panel[index] = new JPanel();
            label[index] = new JLabel(kisi.toString());
            kisi.xCoordSinirlarisag = kisi.annesi.xCoordSinirlarisol + (kisi.annesi.siraCocuk * ((kisi.annesi.xCoordSinirlarisag - kisi.annesi.xCoordSinirlarisol) / kisi.annesi.cocuklari.size()));
            kisi.xCoordSinirlarisol = kisi.annesi.xCoordSinirlarisol + (kisi.annesi.siraCocuk - 1) * ((kisi.annesi.xCoordSinirlarisag - kisi.annesi.xCoordSinirlarisol) / kisi.annesi.cocuklari.size());
            kisi.xCoord = (kisi.xCoordSinirlarisag + kisi.xCoordSinirlarisol) / 2;


            panel[index].setBounds(kisi.xCoord, 20 + (kisi.nesil * 100), 100, 50);
            panel[index].add(label[index]);
            label[index].setBounds(10, 10, 70, 70);
            kisi.labelindex = index;
            kisi.siraKendi = kisi.annesi.siraCocuk;
            kisi.annesi.siraCocuk++;
            if (kisi.cinsiyet.equals(Cinsiyet.KIZ))
                panel[index].setBackground(Color.PINK);
            else
                panel[index].setBackground(Color.GRAY);

            soyAgaciPanel.add(panel[index]);
            index++;
        }

        if (kisi.esi != null && kisi != FamilyTree.root && kisi.esi.adi != null) {
            panel[index - 1].setLocation(panel[index - 1].getX() - 55, panel[index - 1].getY());
            panel[index] = new JPanel();
            label[index] = new JLabel(kisi.esi.toString());
            panel[index].setBounds(panel[index - 1].getX() + 110, 20 + (kisi.esi.nesil * 100), 100, 50);
            panel[index].add(label[index]);
            label[index].setBounds(10, 10, 70, 70);
            if (kisi.esi.cinsiyet.equals(Cinsiyet.KIZ))
                panel[index].setBackground(Color.PINK);
            else
                panel[index].setBackground(Color.GRAY);

            soyAgaciPanel.add(panel[index]);
            kisi.esi.labelindex = index;
            index++;
        }

        for (int j = 0; j < kisi.cocuklari.size(); j++) {
            agacYazdir(kisi.cocuklari.get(j));

        }
    }
}
