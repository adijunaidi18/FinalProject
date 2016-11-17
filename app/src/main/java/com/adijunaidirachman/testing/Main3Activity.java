package com.adijunaidirachman.testing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by adi.rachman on 11/15/2016.
 */

public class Main3Activity extends AppCompatActivity {
    public static final String DEFAULT = "N/A";
    CheckBox whippedCream, chocolate;
    TextView priceTextView, quantityTextView, myText;
    Button myButton;
    EditText myInput;
    String name = DEFAULT;
    int quantity = 0;
    int hargaKopi = 10000;
    int krim = 5000;
    int coklat = 5000;
    public int total_final;
    String priceMessage = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        whippedCream = (CheckBox) findViewById(R.id.cream_coffee);
        chocolate = (CheckBox) findViewById(R.id.cream_coffee2);
        priceTextView = (TextView) findViewById(R.id.price_text_view);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        myButton = (Button) findViewById(R.id.order_button);
        myText = (TextView) findViewById(R.id.or);
        myInput = (EditText) findViewById(R.id.EnterName);

    }


    public void reviewOrder(View view) {

        boolean has_chocolate = chocolate.isChecked();
        boolean has_whippedCream = whippedCream.isChecked();
        //setText();
        Editable nameEditable = myInput.getText();
        name = nameEditable.toString();


        if (name.matches("")){
            Toast.makeText(Main3Activity.this, "Anda lupa harus memasukkan nama..",
                    Toast.LENGTH_SHORT).show();
        } else if (quantity == 0) {
            Toast.makeText(Main3Activity.this, "Anda belum memesan apapun..",
                    Toast.LENGTH_SHORT).show();
        } else {

            priceMessage = "PESAN UNTUK " + name.toUpperCase() + "\n\n" + quantity + " CANGKIR KOPI.\n" + "TOTAL HARGA\t:\tRp " + quantity * hargaKopi;

            if (has_whippedCream == true) {
                int kopikrim = quantity * hargaKopi + krim;
                priceMessage = "PESAN UNTUK " + name.toUpperCase() + "\n\n" + quantity + " CANGKIR KOPI DENGAN WHIPPED CREAM.\n" + "TOTAL HARGA\t:\tRp " + kopikrim  ;
            }
            if (has_chocolate == true) {
                int kopicoklat = quantity * hargaKopi + coklat;
                priceMessage = "PESAN UNTUK " + name.toUpperCase() + "\n\n" + quantity + " CANGKIR KOPI DENGAN CHOCOLATE.\n" + "TOTAL HARGA\t:\tRp " + kopicoklat;

            }
            if (has_chocolate == true && has_whippedCream == true) {
                 total_final = quantity * hargaKopi + coklat + krim;
                priceMessage = "PESAN UNTUK " + name.toUpperCase() + "\n\n" + quantity + " CANGKIR KOPI DENGAN WHIPPED CREAM & CHOCOLATE.\n" + "TOTAL HARGA\t:\tRp " + total_final;
            }

            priceMessage = priceMessage + "\n\n KLIK TOMBOL ORDER UNTUK KONFIRMASI \n TERIMA KASIH SUDAH MEMESAN KOPI KAMI!!";
            displayMessage(priceMessage);

        }

    }


    public void submitOrder(View view) {

        Toast.makeText(Main3Activity.this, "Opening your Email App. Please Wait.",
                Toast.LENGTH_SHORT).show();

        String emailString = "NAMA : " + name.toUpperCase() +
                             "\nQUANTITY : " + quantity +
                             "\nWHIPPED CREAM : " + whippedCream.isChecked() +
                             "\nCHOCOLATE : " + chocolate.isChecked() +
                             "\nTOTAL : " + total_final +
                             "\nTHANK YOU";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.order_summary_email_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT, emailString);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);

    }

    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);

        if (quantity < 0){
            quantity = 0;
            Toast.makeText(this, "Angka pesanan tidak boleh kurang dari 0", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {

        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    public void displayMessage(String message) {

        priceTextView.setText(message);
    }
}
