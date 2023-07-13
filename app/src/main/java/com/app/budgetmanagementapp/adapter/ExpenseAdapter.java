package com.app.budgetmanagementapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.budgetmanagementapp.R;
import com.app.budgetmanagementapp.model.ExpenseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    private List<ExpenseModel> expenseList;
    private Map<String, Double> categoryTotalMap;
    public ExpenseAdapter() {
        this.expenseList = null;    this.categoryTotalMap = new HashMap<>();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryTextView;
        public TextView amountTextView;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
        }
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense, parent, false);
        return new ExpenseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        ExpenseModel model = expenseList.get(position);
        holder.categoryTextView.setText(model.getCategory());
        holder.amountTextView.setText(model.getAmount());
    }

    @Override
    public int getItemCount() {
        return  expenseList != null ? expenseList.size() : 0;
    }

    public void setDataList(List<ExpenseModel> expenses) {
        expenseList =filterIncome(expenses);
        notifyDataSetChanged();
    }
    private List<ExpenseModel> filterIncome(List<ExpenseModel> expenses) {
        List<ExpenseModel> incomeList = new ArrayList<>();
        for (ExpenseModel expense : expenses) {
            if ("Income".equals(expense.getMoneyType())) {
                incomeList.add(expense);
            }
        }
        return incomeList;
    }




}
