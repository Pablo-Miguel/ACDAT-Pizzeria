package com.example.acdat_pizzeria.vista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.acdat_pizzeria.R;
import com.example.acdat_pizzeria.databinding.ViewPizzaItemBinding;
import com.example.acdat_pizzeria.modelo.Pizza;

import java.util.ArrayList;

public class PizzasAdapter extends RecyclerView.Adapter<PizzasAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Pizza> listaPizzas;
    private View.OnClickListener listener;

    public PizzasAdapter(ArrayList<Pizza> listaPizzas) {
        this.listaPizzas = listaPizzas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewPizzaItemBinding binding = ViewPizzaItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        binding.getRoot().setOnClickListener(this);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(listaPizzas.get(position));
    }

    @Override
    public int getItemCount() {
        return listaPizzas.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (this.listener != null) {
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewPizzaItemBinding binding;
        public ViewHolder(ViewPizzaItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Pizza pizza){
            binding.txtPizza.setText(pizza.getNombre());
            binding.imagen.setImageResource(pizza.getRef_img());
        }

    }
}
