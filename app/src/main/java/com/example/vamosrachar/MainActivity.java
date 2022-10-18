package com.example.vamosrachar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    double num1=0.0,num2=0.0,num3=0.0;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final DecimalFormat dff = new DecimalFormat("0");
    private TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Elementos visuais
        EditText valor = (EditText)findViewById(R.id.caixa_valor);
        EditText numerodepessoas = (EditText)findViewById(R.id.caixa_numerodepessoas);
        TextView resultado = (TextView)findViewById(R.id.caixa_resultado);
        Button botao_compartilhar = (Button)findViewById(R.id.botao);
        TextView textView4 = (TextView)findViewById(R .id.textView4);
        Button botao_TTS = (Button)findViewById(R.id.botao_TTS);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if(i!=TextToSpeech.ERROR){
                    // To Choose language of speech
                    tts.setLanguage(Locale.getDefault());
                }
            }
        });



        //Digitando o valor da conta
        valor.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if ((valor.getText().length() > 0)){



                        if(valor.getText().toString().equals(".")){valor.setText("0.");
                        valor.setSelection(valor.getText().length());}



                        String primeiraLetra = String.valueOf(valor.getText().toString().charAt(0));

                        if ((primeiraLetra.equals("0"))&(!valor.getText().toString().contains("0."))&((valor.getText().length() > 1)))
                        {

                            String resto = valor.getText().toString().substring(1);
                            valor.setText(resto);
                            if(valor.getText().length() == 1){valor.setSelection(valor.getText().length());}



                        }










                            num1 = Double.parseDouble(valor.getText().toString());

                            if(num2!=0){
                                num3 = num1 / num2;
                                if((numerodepessoas.getText().length() > 0)&(valor.getText().length() > 0)){
                                    textView4.setText("Resultado:");
                                    resultado.setText(df.format(num3)+" R$");}
                            }

                        }else{
                            textView4.setText("Coloque os valores");
                            resultado.setText("");
                        }
                    }





                }
        );





        //Digitando o nº de pessoas
        numerodepessoas.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (numerodepessoas.getText().length() > 0){





                            String primeiraLetra = String.valueOf(numerodepessoas.getText().toString().charAt(0));
                            if ((primeiraLetra.equals("0"))&(!numerodepessoas.getText().toString().contains("0."))&((numerodepessoas.getText().length() > 1)))
                            {

                                String resto = numerodepessoas.getText().toString().substring(1);
                                numerodepessoas.setText(resto);
                                if(numerodepessoas.getText().length() == 1){numerodepessoas.setSelection(numerodepessoas.getText().length());}



                            }






                            num2 = Double.parseDouble(numerodepessoas.getText().toString());
                            if(num2!=0) {
                                num3 = num1 / num2;
                                if((numerodepessoas.getText().length() > 0)&(valor.getText().length() > 0)){
                                textView4.setText(getString(R.string.Resultado));
                                resultado.setText(df.format(num3)+" R$");}
                            }



                        }else{
                            textView4.setText(R.string.Coloque_os_valores);
                            resultado.setText("");
                        }
                    }





                }
        );




        //Botao para compartilhar
        botao_compartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent shareintent = new Intent();
                shareintent.setAction(Intent.ACTION_SEND);
                shareintent.putExtra(Intent.EXTRA_TEXT,"Rachando " + df.format(num1) + " reais para " + dff.format(num2) + " pessoas dá " + df.format(num3) + " reais para cada.");
                shareintent.setType("text/plain");
                startActivity(shareintent);



            }
        });



        //Botao para o TTS
        botao_TTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((numerodepessoas.getText().length() > 0)&&(valor.getText().length() > 0)&&(num1!=0)&&(num2!=0)){
                    tts.speak("Rachando " + df.format(num1) + " reais para " + dff.format(num2) + " pessoas dá " + df.format(num3) + " reais para cada.", TextToSpeech.QUEUE_FLUSH, null);

                }else{tts.speak("Coloque os valores!", TextToSpeech.QUEUE_FLUSH, null);}



            }
        });



    }
}