package com.robsonteam.lab3fxapp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameControler
{
    @FXML
    private BorderPane Panel;
    @FXML
    private Button But1;
    @FXML
    private Button But2;

    @FXML
    private Button But3;

    @FXML
    private Button But4;

    @FXML
    private Button But5;

    @FXML
    private Button But6;

    @FXML
    private Button But7;

    @FXML
    private Button But8;

    @FXML
    private Button But9;

    @FXML
    private Label napis;


    private boolean x;              //Bool mówi czy jest kolej na X czy O
    private boolean end = false;    //Czy gra się zakończyła
    private int zp = 0;             //Z - zajete P- pola

    private int winX[] = new int[8];    //Indexy zwyciestwa dla x
    private int winO[] = new int[8];    //Indexy zwyciestwa dla o

    private Button guziczki[];          //Deklaracja tablicy na przyciski
    private int[][] winCodes = {{1,4,8}, {2,4}, {3, 4, 7},{1, 5}, {2, 5, 7, 8}, {3, 5},{1, 6, 7}, {2, 6},{3, 6, 8}};
    //Kody zwyciestw dla kazdego pola
    //Gre na sie wygrac na 8 sposobów, pól jest 9
    @FXML
    public void initialize()
    {
        this.guziczki = new Button[]{But1, But2, But3, But4, But5, But6, But7, But8, But9};
    }

    @FXML
    void Restart()                      //Funkcja restart
    {
        if(end ==true)
        {
            for(int i = 0; i < 8; i++)      //Resetuje indexy zwyciestwa dla x i o
            {
                winX[i]=0;
                winO[i]=0;
            }
            for(int i = 0; i < 9; i++)      //Resetuje teksty i aktywuje przyciski
            {
                guziczki[i].setText("");
                guziczki[i].setDisable(false);
            }
            napis.setText("Fight!");        //Ustawaia napis do góry oraz resetuje licznik zajetych pól
            zp=0;
            end = false;                //Koniec zresetowany
        }
    }

    /**
     * Funkcja ta w zależnosci kto wygrał ustwawia odpowiednie napisy, oraz wyłącza wszytkie przyciski
     * @param kto false = zwyciestwo X,true = zwyciestwo O
     */
    private void Win(boolean kto)
    {
        if(kto)
        {
            napis.setText("Wygrał X!");
            end = true;
        }else {
            napis.setText("Wygrał O!");
            end = true;
        }
        for(int i = 0; i < 9; i++)
        {
            guziczki[i].setDisable(true);
        }
    }

    /**
     * Funkcja ta w zależności od wciśnietego przycisku inkrementuje indexy zwycięstwa dla x i o.
     * Pobieram od przysku jego ID i wyciągam z niego liczbę, inkrementuje odpowiednią wartość i sprawdzam czy
     * ktoś już nie wygrał
     * @param but przycisk który został wduszony
     */
    private void CzyWin(Button but)
    {
        String s = but.getId();
        int index = Character.getNumericValue(s.charAt(3))-1;

        for(int j = 0; j < winCodes[index].length; j++)
        {
            if(x)
            {
                winX[winCodes[index][j]-1]++;
            }else {
                winO[winCodes[index][j]-1]++;
            }
        }
        for(int i = 0; i < 8; i++)
        {
            if(winX[i]>= 3)
            {
                Win(false);
            }else if(winO[i]>=3)
                Win(true);
        }

    }
    @FXML
    void ButAction(ActionEvent event)
    {
        Button btn = (Button) event.getSource();
        if(end == false)                            //Czy gra sie jeszcze toczy?
        {
            if(x)                                   //Jesli jest kolej X
            {
                btn.setText("X");
                x=false;
            }else {                                 //Jesli kolej O
                btn.setText("O");
                x=true;
            }
            btn.setDisable(true);                   //Niezaleznie kto miał ruch, wyłaczam naciśnięty przycisk
            zp++;                                   //Inkrementuje ilość zajętych pól
            CzyWin(btn);                            //Sprawdzam kto wygrał
        }
        if(zp >= 9)                                 //Jeżeli zająłem wszytie pola ustawiam flagę end na true.
        {
            end=true;
        }
    }
}
