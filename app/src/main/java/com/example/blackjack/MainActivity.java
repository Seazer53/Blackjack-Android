package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView display, result;
    ImageButton imageButton, imageButton2, imageButton3, imageButton4, imageButton5, imageButton6,
    imageButton7, imageButton8, imageButton9, imageButton10, imageButton11, imageButton12;
    Button button, button2, button3, button4;

    List<String> userDeck = new ArrayList<>();
    List<String> botDeck = new ArrayList<>();
    Map<String,Integer> deck = new HashMap<>();
    List<String> cardNames = new ArrayList<>();
    Context context;
    int money = 100;
    int bet = 10;
    boolean gameOver = false;
    int userDeckValue = 0;
    int botDeckValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        //puting cards to the deck
        deck.put("two_of_clubs", 2);
        deck.put("three_of_clubs", 3);
        deck.put("four_of_clubs", 4);
        deck.put("five_of_clubs", 5);
        deck.put("six_of_clubs", 6);
        deck.put("seven_of_clubs", 7);
        deck.put("eight_of_clubs", 8);
        deck.put("nine_of_clubs", 9);
        deck.put("ten_of_clubs", 10);
        deck.put("ace_of_clubs", 11);
        deck.put("jack_of_clubs", 10);
        deck.put("queen_of_clubs", 10);
        deck.put("king_of_clubs", 10);

        deck.put("two_of_diamonds", 2);
        deck.put("three_of_diamonds", 3);
        deck.put("four_of_diamonds", 4);
        deck.put("five_of_diamonds", 5);
        deck.put("six_of_diamonds", 6);
        deck.put("seven_of_diamonds", 7);
        deck.put("eight_of_diamonds", 8);
        deck.put("nine_of_diamonds", 9);
        deck.put("ten_of_diamonds", 10);
        deck.put("ace_of_diamonds", 11);
        deck.put("jack_of_diamonds", 10);
        deck.put("queen_of_diamonds", 10);
        deck.put("king_of_diamonds", 10);

        deck.put("two_of_hearts", 2);
        deck.put("three_of_hearts", 3);
        deck.put("four_of_hearts", 4);
        deck.put("five_of_hearts", 5);
        deck.put("six_of_hearts", 6);
        deck.put("seven_of_hearts", 7);
        deck.put("eight_of_hearts", 8);
        deck.put("nine_of_hearts", 9);
        deck.put("ten_of_hearts", 10);
        deck.put("ace_of_hearts", 11);
        deck.put("jack_of_hearts", 10);
        deck.put("queen_of_hearts", 10);
        deck.put("king_of_hearts", 10);

        deck.put("two_of_spades", 2);
        deck.put("three_of_spades", 3);
        deck.put("four_of_spades", 4);
        deck.put("five_of_spades", 5);
        deck.put("six_of_spades", 6);
        deck.put("seven_of_spades", 7);
        deck.put("eight_of_spades", 8);
        deck.put("nine_of_spades", 9);
        deck.put("ten_of_spades", 10);
        deck.put("ace_of_spades", 11);
        deck.put("jack_of_spades", 10);
        deck.put("queen_of_spades", 10);
        deck.put("king_of_spades", 10);

        display = findViewById(R.id.textView);
        String info = "Money: " + money + "₺ \nBet:" + bet + "₺";
        display.setText(info);

        result = findViewById(R.id.textView2);

        imageButton = findViewById(R.id.imageButton);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton4 = findViewById(R.id.imageButton4);
        imageButton5 = findViewById(R.id.imageButton5);
        imageButton6 = findViewById(R.id.imageButton6);
        imageButton7 = findViewById(R.id.imageButton7);
        imageButton8 = findViewById(R.id.imageButton8);
        imageButton9 = findViewById(R.id.imageButton9);
        imageButton10 = findViewById(R.id.imageButton10);
        imageButton11 = findViewById(R.id.imageButton11);
        imageButton12 = findViewById(R.id.imageButton12);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        setCardVisiblity();
        giveCards();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            if (!gameOver) {
                String card = hitCard(cardNames, userDeck);
                int id = context.getResources().getIdentifier(card, "drawable", context.getPackageName());
                userDeckValue = checkCardValues(deck, userDeck);

                String info = "Money: " + money + "₺ \nBet: " + bet + "₺\nUser Deck Value: " + userDeckValue;
                display.setText(info);

                if (userDeckValue > 21) {
                    result.setText(R.string.you_lost);
                    gameOver = true;
                    money -= bet;
                    int id2 = context.getResources().getIdentifier(botDeck.get(0), "drawable", context.getPackageName());
                    imageButton.setImageResource(id2);
                }

                switch(userDeck.size()) {
                    case 3:
                        imageButton9.setImageResource(id);
                        imageButton9.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        imageButton10.setImageResource(id);
                        imageButton10.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        imageButton11.setImageResource(id);
                        imageButton11.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        imageButton12.setImageResource(id);
                        imageButton12.setVisibility(View.VISIBLE);
                        break;
                }
            }

        } else if (v.getId() == R.id.button2) {
            while ((botDeckValue <= 21) && !gameOver) {
                botDeckValue = checkCardValues(deck, botDeck);

                if (botDeckValue <= 17) {
                    String card = hitCard(cardNames, botDeck);
                    int id = context.getResources().getIdentifier(card, "drawable", context.getPackageName());

                    botDeckValue = checkCardValues(deck, botDeck);

                    if (botDeckValue > 21) {
                        int id2 = context.getResources().getIdentifier(botDeck.get(0), "drawable", context.getPackageName());
                        imageButton.setImageResource(id2);
                        result.setText(R.string.you_win);
                        gameOver = true;
                        money += bet;
                    }

                    switch(botDeck.size()) {
                        case 3:
                            imageButton3.setImageResource(id);
                            imageButton3.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            imageButton4.setImageResource(id);
                            imageButton4.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            imageButton5.setImageResource(id);
                            imageButton5.setVisibility(View.VISIBLE);
                            break;
                        case 6:
                            imageButton6.setImageResource(id);
                            imageButton6.setVisibility(View.VISIBLE);
                            break;
                    }

                } else {
                    if (userDeckValue > botDeckValue) {
                        int id = context.getResources().getIdentifier(botDeck.get(0), "drawable", context.getPackageName());
                        imageButton.setImageResource(id);
                        result.setText(R.string.you_win);
                        gameOver = true;
                        money += bet;
                        break;
                    } else if (userDeckValue < botDeckValue) {
                        int id = context.getResources().getIdentifier(botDeck.get(0), "drawable", context.getPackageName());
                        imageButton.setImageResource(id);
                        result.setText(R.string.you_lost);
                        gameOver = true;
                        money -= bet;
                        break;
                    } else if(botDeckValue == userDeckValue) {
                        int id = context.getResources().getIdentifier(botDeck.get(0), "drawable", context.getPackageName());
                        imageButton.setImageResource(id);
                        result.setText(R.string.draw);
                        gameOver = true;
                        break;
                    }
                }
            }

        } else if (v.getId() == R.id.button3) {
            if (!gameOver) {
                if (money > 0) {
                    money -= 10;
                    bet += 10;
                } else {
                    Toast.makeText(this, "Insufficient money", Toast.LENGTH_SHORT).show();
                }

                String info = "Money: " + money + "₺ \nBet: " + bet + "₺\nUser Deck Value: " + userDeckValue;
                display.setText(info);
            }

        } else if (v.getId() == R.id.button4) {
            if (gameOver) {
                userDeck.clear();
                botDeck.clear();
                result.setText("");
                cardNames.clear();
                gameOver = false;
                userDeckValue = 0;
                botDeckValue = 0;

                if (money < 0) {
                    money = 10;
                    bet = 10;
                }

                String info = "Money: " + money + "₺ \nBet: " + bet + "₺\nUser Deck Value: " + userDeckValue;
                display.setText(info);

                int id = context.getResources().getIdentifier("back", "drawable", context.getPackageName());
                imageButton.setImageResource(id);

                setCardVisiblity();
                giveCards();
            } else {
                Toast.makeText(this, "Game is not over!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void giveCards() {
        cardNames.addAll(deck.keySet());
        Collections.shuffle(cardNames);

        String card;

        for (int x = 0; x < 3; x++) {
            if (botDeck.size() < 2) {
                card = cardNames.remove(0);
                botDeck.add(card);
                int id = context.getResources().getIdentifier(card, "drawable", context.getPackageName());
                imageButton2.setImageResource(id);
            } else if (userDeck.size() < 2) {
                card = cardNames.remove(0);
                userDeck.add(card);
                int id = context.getResources().getIdentifier(card, "drawable", context.getPackageName());
                imageButton7.setImageResource(id);

                card = cardNames.remove(0);
                userDeck.add(card);
                id = context.getResources().getIdentifier(card, "drawable", context.getPackageName());
                imageButton8.setImageResource(id);
            }
        }

        imageButton2.setVisibility(View.VISIBLE);
        imageButton7.setVisibility(View.VISIBLE);
        imageButton8.setVisibility(View.VISIBLE);

        userDeckValue = checkCardValues(deck, userDeck);
        botDeckValue = checkCardValues(deck, botDeck);

        String info = "Money: " + money + "₺ \nBet: " + bet + "₺\nUser Deck Value: " + userDeckValue;
        display.setText(info);
    }

    public String hitCard(List<String> deck, List<String> playerDeck) {
        String card = deck.remove(0);
        playerDeck.add(card);

        return card;
    }

    public int checkCardValues(Map<String, Integer> deck, List<String> playerDeck) {
        int totalValue = 0;
        int aceCount = 0;

        for (String pCard : playerDeck) {
            for (Map.Entry<String, Integer> card : deck.entrySet()) {
                if (pCard.equals(card.getKey())) {
                    totalValue += card.getValue();

                    if (pCard.startsWith("ace")) {
                        aceCount += 1;
                    }

                    if (totalValue > 21 && aceCount > 0) {
                        totalValue -= 10;
                        aceCount -= 1;
                    }
                }
            }
        }

        return totalValue;
    }

    public void setCardVisiblity() {
        imageButton3.setVisibility(View.INVISIBLE);
        imageButton4.setVisibility(View.INVISIBLE);
        imageButton5.setVisibility(View.INVISIBLE);
        imageButton6.setVisibility(View.INVISIBLE);
        imageButton9.setVisibility(View.INVISIBLE);
        imageButton10.setVisibility(View.INVISIBLE);
        imageButton11.setVisibility(View.INVISIBLE);
        imageButton12.setVisibility(View.INVISIBLE);
    }


}