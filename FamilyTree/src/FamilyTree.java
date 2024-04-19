import java.awt.*;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class FamilyTree {
    static ArrayList<Kisi> herkes= new ArrayList<Kisi>();
    static ArrayList<Kisi> herkes2= new ArrayList<Kisi>();
    public static void main() {

        FamilyTree familytree1= new FamilyTree();



        for(int i=0;i<herkes.size();i++)
        {
            Kisi eklenecekKisi=herkes.get(i);
            familytree1.addMember(eklenecekKisi.id,eklenecekKisi.adi,eklenecekKisi.soyadi,eklenecekKisi.dogumtarihi,eklenecekKisi.esininAdi,eklenecekKisi.annesininAdi,eklenecekKisi.babasininAdi,eklenecekKisi.kangrubu,eklenecekKisi.meslegi,eklenecekKisi.evliMi, eklenecekKisi.kizliksoyadi,eklenecekKisi.cinsiyet,eklenecekKisi.yas);
        }

        printPreOrder(root);

        herkes2Add(root);
        Arayuz.main();



    }
    public static void printPreOrder(Kisi kisi){
        if(kisi!=null)
        {
            kisi.printPreOrder();
        }
    }
    public static Kisi root;

    public FamilyTree() {

        root = null;
    }

    public void addMember(int id,String name,String surname,String birthDate, String spouseName, String motherName, String fatherName,String bloodType,String job,Boolean married,String kizlikSoyadi,Cinsiyet gender,int yas) {

        if (root == null) {
            root = new Kisi(id,name,surname,birthDate,spouseName,motherName,fatherName,bloodType,job,married,kizlikSoyadi,gender,yas);
            root.nesil=0;
            root.annesi=null;
            root.babasi=null;
            return;
        }

        Kisi father = search(root, fatherName,surname);
        Kisi mother = search(root, motherName,surname);
        Kisi spouse= search(root,spouseName,surname);
        Kisi member = new Kisi(id,name,surname,birthDate,spouseName,motherName,fatherName,bloodType,job,married,kizlikSoyadi,gender,yas);
        if (father != null && father.getEsininAdi()!=null&&father.getEsininAdi().equals(motherName)) {
            member.babasi=father;
            member.nesil=father.nesil+1;
            father.addChild(member);
            father.esi.addChild(member);
        }
        else if(mother != null && mother.getEsininAdi()!=null&&mother.getEsininAdi().equals(fatherName))
        {

            member.annesi=mother;
            member.nesil=mother.nesil+1;
            mother.addChild(member);
            mother.esi.addChild(member);
        }


        if(spouse!=null && spouse.soyadi.equals(surname))
        {
            member.esi=spouse;
            spouse.esi=member;

        }
    }
    static int ayninesil=0;
    public static void ayniNesilSayisiBul(Kisi kisi,int arananNesil)
    {
        if(kisi.nesil==arananNesil)
        {
            ayninesil++;
        }
        for(int i=0;i<kisi.cocuklari.size();i++)
        {
            ayniNesilSayisiBul(kisi.cocuklari.get(i),arananNesil);
        }
    }


    public static Kisi search(Kisi member, String name, String surname) {

        if (member.getAdi().equals(name)) {
            return member;
        }


        for (Kisi child : member.getCocuklari()) {
            Kisi found = search(child, name,surname);
            if (found != null) {
                return found;
            }
        }

        if(member.evliMi && member.esi.adi.equals(name))
        {
            return member.esi;
        }
        return null;
    }
    public static void akrabalikBul(String namesurname,String akrabanameSurname)
    {
        System.out.println("Akrabalık:");
        Arayuz.konsol.setText(Arayuz.konsol.getText()+"\nAkrabalık:");
        String surname= namesurname.substring(namesurname.lastIndexOf(" ")+1,namesurname.length()) ;
        String name=namesurname.substring(0,namesurname.indexOf(" "));
        String akrabaSurname= akrabanameSurname.substring(akrabanameSurname.lastIndexOf(" ")+1,akrabanameSurname.length()) ;
        String akrabaname=akrabanameSurname.substring(0,akrabanameSurname.indexOf(" "));
        Kisi aranan=search(root,name,surname);
        akrabalikBulyukari(aranan);
        akrabalikBulasagi(root,akrabaname,akrabaSurname);

    }
    public static Kisi akrabalikBulasagi(Kisi member,String name,String surname) {
        if (member.getAdi().equals(name) && member.soyadi.equals(surname)) {
            return member;
        }


        for (Kisi child : member.getCocuklari()) {
            Kisi found = akrabalikBulasagi(child, name, surname);
            if (found != null) {
                if (found.cinsiyet.equals(Cinsiyet.ERKEK)) {
                    System.out.println("oğlunun");
                    Arayuz.konsol.setText(Arayuz.konsol.getText()+"\noğlunun");
                } else
                {
                    System.out.println("kızının");
                    Arayuz.konsol.setText(Arayuz.konsol.getText()+"\nkızının");
                }


                return found;
            }

        }
        return  null;
    }
    public static void akrabalikBulyukari(Kisi kisi){
        if (kisi.babasi!=null) {
            if ( kisi.babasi==root || kisi.babasi.babasi != null || kisi.babasi.annesi != null) {
                System.out.println("babasinin");
                Arayuz.konsol.setText(Arayuz.konsol.getText() + "\nbabasının");
                akrabalikBulyukari(kisi.babasi);
            }}
        if(kisi.annesi!=null)
             if ( kisi.annesi==root || kisi.annesi.annesi != null || kisi.annesi.babasi != null) {
                System.out.println("annesinin");
                Arayuz.konsol.setText(Arayuz.konsol.getText()+"\nannesinin");
                akrabalikBulyukari(kisi.annesi);
            }
        }


    public static void nesilBelirle(Kisi kisi){
        if(kisi==root){
            kisi.esi.nesil=kisi.nesil;
        }
        else{
            if(kisi.annesi!=null){
                kisi.nesil=kisi.annesi.nesil;
            }
            else{
                kisi.nesil=kisi.babasi.nesil;
            }
            if(kisi.cocuklari.size()!=0)
            for(int i=0;i<kisi.cocuklari.size();i++){
                    nesilBelirle(kisi.cocuklari.get(i));
            }
        }

    }

    public static int nesilBilgisiBul(Kisi kisi){
        if(root == null) return 0;
        Integer h=0;

        if(kisi.cocuklari!=null)
        for(int n=0;n<kisi.cocuklari.size();n++){
            h = Math.max(h, nesilBilgisiBul(kisi.cocuklari.get(n)));
        }

        return h+1;
    }
    public static int nesilBilgisiGoster(Kisi kisi)
    {
        kisi.nesil=nesilBilgisiBul(kisi);
        return kisi.nesil;
    }
    public static int nesilBilgisiGoster(String name, String surname)
    {
        nesilBilgisiBul(root);
        Kisi aranan=search(root,name,surname);
        nesilBelirle(root);
        return maxNesil()- aranan.nesil;

    }
    public static int maxNesil(){
        int max=nesilBilgisiBul(root);
        return  max;
    }

    public void soyAgaciGoster(String name,String surname)
    {
        Kisi aranan=search(root,name,surname);
        printPreOrder(aranan);
    }
    public static ArrayList<Kisi> cocuguOlmayandugumler=new ArrayList<>();

    public static void cocuguOlmayanDugumler(Kisi kisi){
        if(kisi.cocuklari.size()==0)
        {
            cocuguOlmayandugumler.add(kisi);
            if(kisi.evliMi==true && kisi.esi!=null){
                cocuguOlmayandugumler.add(kisi.esi);
            }
            return;
        }
        for(int i=0;i<kisi.cocuklari.size();i++)
        {
            cocuguOlmayanDugumler(kisi.cocuklari.get(i));
        }
    }
    static int k=0;
    public static void yazdirCocuguOlmayanDugumler(){
        cocuguOlmayandugumler.clear();
        k=0;
        cocuguOlmayanDugumler(root);
        System.out.println("Çocuğu olmayan kişiler:");
        Arayuz.konsol.setText(Arayuz.konsol.getText()+"\n"+"Çocuğu olmayan kişiler:");
        for (int i = 0; i < cocuguOlmayandugumler.size() - 1; i++)
            for (int j = 0; j < cocuguOlmayandugumler.size() - i - 1; j++)
                if (cocuguOlmayandugumler.get(j).yas > cocuguOlmayandugumler.get(j+1).yas){
                    Kisi temp= cocuguOlmayandugumler.get(j);
                    cocuguOlmayandugumler.set(j,cocuguOlmayandugumler.get(j+1));
                    cocuguOlmayandugumler.set(j+1,temp);
                    k++;
                    System.out.println(k+"."+cocuguOlmayandugumler.get(j)+" ve "+cocuguOlmayandugumler.get(j+1)+" yer değiştirdi");
                }

        for (int i = 0; i < cocuguOlmayandugumler.size(); i++) {
            System.out.println(cocuguOlmayandugumler.get(i).toString());
            Arayuz.konsol.setText(Arayuz.konsol.getText()+"\n"+cocuguOlmayandugumler.get(i).toString());
        }
    }
    public static void kanGrubuOlanlariListele(String kangrubu)
    {
        System.out.println("Kan grubu "+ kangrubu+ " olanlar:");
        Arayuz.konsol.setText(Arayuz.konsol.getText()+"\n"+"Kan grubu "+ kangrubu+ " olanlar:");
        for(int i=0;i<herkes2.size();i++)
        {
            if(herkes2.get(i).kangrubu.equals(kangrubu))
            {
                System.out.println(herkes2.get(i).toString());
                Arayuz.konsol.setText(Arayuz.konsol.getText()+"\n"+herkes2.get(i).toString());
                Arayuz.panel[herkes2.get(i).labelindex].setBackground(Color.RED);
            }
        }
    }

    public static void herkes2Add(Kisi kisi){
        if(kisi==root)
        {
            herkes2.add(kisi);
        }
        if(kisi.evliMi && kisi.esi!=null)
        {
            kisi.esi.nesil=kisi.nesil;
            herkes2.add(kisi.esi);
        }
        herkes2.addAll(kisi.cocuklari);

            for (int i = 0; i < kisi.cocuklari.size(); i++) {
                herkes2Add(kisi.cocuklari.get(i));
            }

    }
    static ArrayList<Kisi> meslegiDevamArray= new ArrayList<>();
    public static void meslegiDevamEttirenler(Kisi kisi)
    {
        if(kisi.cocuklari.size()!=0)
            for(int i=0;i<kisi.cocuklari.size();i++){
                if(kisi.cocuklari.get(i).meslegi.equals(kisi.meslegi) || (kisi.esi.adi!=null && kisi.cocuklari.get(i).meslegi.equals(kisi.esi.meslegi))){
                    meslegiDevamArray.add(kisi.cocuklari.get(i));
                    Arayuz.panel[kisi.cocuklari.get(i).labelindex].setBackground(Color.RED);
                    System.out.println("Mesleğini Devam Ettirenler:"+ kisi.cocuklari.get(i).toString()+ " Mesleği:"+ kisi.cocuklari.get(i).meslegi);
                    Arayuz.konsol.setText(Arayuz.konsol.getText()+"\n"+"Mesleğini Devam Ettirenler:"+ kisi.cocuklari.get(i).toString()+ " Mesleği:"+ kisi.cocuklari.get(i).meslegi);
                    meslegiDevamEttirenler(kisi.cocuklari.get(i));
                }
            }
    }

    public static void ismiAyniOlanlar(String isimsoyisim){
        String soyisim= isimsoyisim.substring(isimsoyisim.lastIndexOf(" ")+1,isimsoyisim.length()) ;
        String isim=isimsoyisim.substring(0,isimsoyisim.indexOf(" "));
        recIsmiAyniOlanlar(isim,soyisim,root);

    }
    public static void recIsmiAyniOlanlar(String isim,String soyisim,Kisi kisi){
        if(kisi.adi.equals(isim) && kisi.soyadi.equals(soyisim)){
            Arayuz.panel[kisi.labelindex].setBackground(Color.RED);
            System.out.println("\n"+kisi.adi+" Yaşı: "+kisi.yas);
            Arayuz.konsol.setText(Arayuz.konsol.getText()+"\n"+kisi.adi+" Yaşı: "+kisi.yas);
        }
        if(kisi.esi!=null && kisi.esi.adi!=null)
        if(kisi.esi.adi.equals(isim) && kisi.esi.soyadi.equals(soyisim))
        {
            Arayuz.panel[kisi.esi.labelindex].setBackground(Color.RED);
            System.out.println("\n"+kisi.esi.adi+" Yaşı: "+kisi.yas);
            Arayuz.konsol.setText(Arayuz.konsol.getText()+"\n"+kisi.esi.adi+" Yaşı: "+kisi.esi.yas);
        }
        if(kisi.cocuklari.size()!=0)
            for (int i = 0; i < kisi.cocuklari.size(); i++) {
                recIsmiAyniOlanlar(isim, soyisim, kisi.cocuklari.get(i));
            }
    }

    public static  void ailesiniGoster(Kisi kisi){
        Arayuz.panel[kisi.labelindex].setBackground(Color.RED);
        if(kisi.esi!=null)
        {
            Arayuz.panel[kisi.esi.labelindex].setBackground(Color.RED);
        }
        if(kisi.cocuklari.size()!=0)
            for (int i = 0; i < kisi.cocuklari.size(); i++) {
                ailesiniGoster(kisi.cocuklari.get(i));
            }
    }

}


enum Cinsiyet {
    ERKEK,
    KIZ
}


class Kisi {


    public int id;
    public String adi="";
    public String soyadi="";
    public Cinsiyet cinsiyet;
    public Kisi babasi;
    public String babasininAdi="";
    public Kisi annesi;
    public String annesininAdi="";
    public int nesil;
    public int esID;

    public Kisi() {

    }

    public List<Kisi> getCocuklari() {
        return cocuklari;
    }

    public Kisi esi;
    public String esininAdi="";

    public String kangrubu="";
    public String meslegi="";
    public boolean evliMi;
    public String kizliksoyadi="";
    public List<Kisi> cocuklari;
    public String dogumtarihi;

    public String medeniHali="";
    public int yas;

    int xCoord;

    int yCoord;

    int siraCocuk=1;
    int siraKendi=0;

    int labelindex=0;
    int kardesSayisi;

    int xCoordSinirlarisol;
    int xCoordSinirlarisag=1920;

    public void setEsi(Kisi esi) {
        this.esi = esi;
    }

    public Kisi getEsi() {
        return esi;
    }

    public String getEsininAdi() {
        return esininAdi;
    }

    public String getAdi() {
        return adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public int kardesSayisiBul(){
        if(this.annesi!=null)
        {
            this.kardesSayisi=this.annesi.cocuklari.size();
            return this.kardesSayisi;
        }

        else if(this.babasi!=null)
        {
            this.kardesSayisi=this.babasi.cocuklari.size();
            return this.kardesSayisi;
        }
        else
            this.kardesSayisi=1;
        return 1;
    }

    public Kisi(String adi, String soyadi, Cinsiyet cinsiyet) {
        this.adi = adi;
        this.soyadi = soyadi;
        this.cinsiyet = cinsiyet;
        this.cocuklari = new ArrayList<>();


    }
    public Kisi(int id,String name,String surname,String birthDate, String spouseName, String motherName, String fatherName,String bloodType,String job,Boolean married,String kizlikSoyadi,Cinsiyet gender,int yas) {
        this.adi = name;
        this.soyadi = surname;
        this.cinsiyet = gender;
        this.cocuklari = new ArrayList<>();
        this.esininAdi=spouseName;
        this.id=id;
        this.dogumtarihi=birthDate;
        this.annesininAdi=motherName;
        this.babasininAdi=fatherName;
        this.kangrubu=bloodType;
        this.meslegi=job;
        this.evliMi=married;
        this.kizliksoyadi=kizlikSoyadi;
        this.yas=yas;
        if(cinsiyet.equals(Cinsiyet.KIZ))
        {
            Kisi esi= new Kisi(spouseName,soyadi,Cinsiyet.ERKEK);
            this.esi=esi;
            esi.setEsi(this);
        }
        else {
            Kisi esi= new Kisi(spouseName,soyadi,Cinsiyet.KIZ);
            this.esi=esi;
            esi.setEsi(this);
        }
        String string= birthDate.substring(birthDate.lastIndexOf("/")+1);
        if(!string.equals(""))
        this.yas=2022-Integer.parseInt(string);

    }




    public void setParent(Kisi parent){
        if(parent.cinsiyet.equals(Cinsiyet.ERKEK)) this.babasi=parent;
        else this.annesi=parent;
    }

    public void addChild(Kisi child) {
        this.cocuklari.add(child);
    }

    public String toString() {
        return this.adi + " " + this.soyadi;
    }

    public void kisiBilgiYazdir(Kisi kisi){
        if(kisi.annesi!=null && kisi.babasi!=null)
            System.out.println(kisi.adi+" "+kisi.kizliksoyadi+" "+kisi.soyadi+"\n  Annesi: "+kisi.annesi.toString()+ "\n  Babası: "+kisi.babasi.toString()+" \n Mesleği: "+kisi.meslegi+"\n Yaşı:"+kisi.yas+"\n Nesil:"+kisi.nesil);
        else if(kisi.annesininAdi!=null && kisi.babasininAdi!=null)
            System.out.println(kisi.adi+" "+kisi.soyadi+"\n Annesi: "+kisi.annesininAdi+ "\n Babası: "+kisi.babasininAdi+" \n Mesleği: "+kisi.meslegi+"\n Yaşı:"+kisi.yas+"\n Nesil:"+kisi.nesil);
        else
            System.out.println("  "+kisi.adi+" "+kisi.soyadi+" \n Mesleği: "+kisi.meslegi+"\n Yaşı:"+kisi.yas+"\n Nesil:"+kisi.nesil);
    }
    public void printPreOrder(){
        this.kisiBilgiYazdir(this);


        if(this.evliMi && this.esi!=null)
        {
            System.out.println("  Eşi: ");
            this.kisiBilgiYazdir(this.esi);
        }
        System.out.println("  Çocukları:");
        for(int i=0;i<this.cocuklari.size();i++)
        {
            System.out.println("   "+this.cocuklari.get(i).toString());

        }

        if(this!=null)
        {
            for (int i = 0; i < this.cocuklari.size(); i++) {
                System.out.println(" ");
                this.cocuklari.get(i).printPreOrder();
            }
        }
    }




}

