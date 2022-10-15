package com.example.petmed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class AdminShop extends AppCompatActivity implements View.OnClickListener {

    private CardView cardFood2;
    private CardView cardOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_shop);
        getSupportActionBar().setTitle("Your Shop");


        cardFood2 = (CardView) findViewById(R.id.cardFood2);
        cardOrder = (CardView) findViewById(R.id.cardOrder);

        cardFood2.setOnClickListener(this);
        cardOrder.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.cardFood2:
                intent = new Intent(this, AdminFood.class);
                startActivity(intent);
                break;
            case R.id.cardOrder:
                intent = new Intent(this, AdminOrder.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }
}