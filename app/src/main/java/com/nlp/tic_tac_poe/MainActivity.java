package com.nlp.tic_tac_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nlp.tic_tac_poe.databinding.GameBoardBinding;

import java.util.ArrayList;
import java.util.Arrays;

/*
* Этапы сохранения проекта в git
* git init - инициализация гита в проекта
* git add . добавить все файлы в гиту
* git commit -m "текст сообщения"
* */
public class MainActivity extends AppCompatActivity {
    public GameBoardBinding binding;
    public ArrayList<Button> squaresArray;

    private ColorStateList drawColor;
    private ColorStateList winColor;
    private ColorStateList defaultColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Вызываем родительский метод тот же
        super.onCreate(savedInstanceState);
        binding = GameBoardBinding.inflate(getLayoutInflater());
        //Ссылаемся на интерфейс
        setContentView(binding.getRoot());
        boardInit();

        drawColor = this.getColorStateList(R.color.black);
        winColor = this.getColorStateList(R.color.green);
    }
    public void boardInit(){

        squaresArray = new ArrayList<>();
        squaresArray.add(binding.field1);
        squaresArray.add(binding.field2);
        squaresArray.add(binding.field3);
        squaresArray.add(binding.field4);
        squaresArray.add(binding.field5);
        squaresArray.add(binding.field6);
        squaresArray.add(binding.field7);
        squaresArray.add(binding.field8);
        squaresArray.add(binding.field9);

        for(int i = 0; i < squaresArray.size();i++){
            int finalI = i;
            squaresArray.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!squaresArray.get(finalI).getText().toString().equals(" "))
                        return;
                    if(GameInfo.whoIsNext)
                        squaresArray.get(finalI).setText(GameInfo.firstPlayerSymbol);
                    else
                        squaresArray.get(finalI).setText(GameInfo.secondPlayerSymbol);
                    GameInfo.whoIsNext = !GameInfo.whoIsNext;
                    checkWin();
                    v.setEnabled(false);
                }
            });
        }
    }

    private void checkWin() {
        for (int[] win: GameInfo.winCombination) {
            int xCount = 0;
            int oCount = 0;
            for (int i : win) {
                if (squaresArray.get(i).getText() == GameInfo.firstPlayerSymbol) {
                    xCount++;
                } else if (squaresArray.get(i).getText() == GameInfo.secondPlayerSymbol) {
                    oCount++;
                }
            }
            if (xCount == 3) {
                // победа
                // String title = this.getResources().getString(R.string.x_won);
                Arrays.stream(win).forEach(i -> {
                    squaresArray.get(i).setBackgroundTintList(winColor);
                });
                blockAllButtons();
                /// winDialog(title);
            } else if (oCount == 3) {
                // победа
                Arrays.stream(win).forEach(i -> {
                    squaresArray.get(i).setBackgroundTintList(winColor);
                });
                blockAllButtons();
                // winDialog(context.getResources().getString(R.string.o_won));
            }
        }
        if (squaresArray.stream().allMatch(it -> it.getText().equals(""))) {
            // ничья
            squaresArray.stream().forEach(it -> {
                it.setBackgroundTintList(drawColor);
            });
            // winDialog(context.getResources().getString(R.string.friendship_won));
        }
    }

    private void blockAllButtons() {
        squaresArray.stream().forEach(it -> {
            it.setEnabled(false);
        });
    }

}