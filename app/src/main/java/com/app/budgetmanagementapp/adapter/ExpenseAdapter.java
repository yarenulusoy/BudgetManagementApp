package com.app.budgetmanagementapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
    private ExpenseDeleteListener expenseDeleteListener; // Add this line


    public interface ExpenseDeleteListener {
        void onExpenseDelete(int expenseId);
    }

    public void setExpenseDeleteListener(ExpenseDeleteListener listener) {
        this.expenseDeleteListener = listener;
    }
    public ExpenseAdapter() {
        this.expenseList = null;
        this.categoryTotalMap = new HashMap<>();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryTextView;
        public TextView amountTextView;
      //  public ImageView deleteButton;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
          //  deleteButton = itemView.findViewById(R.id.deleteButton);
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
        /*holder.deleteButton.setOnClickListener(v -> {
            if (expenseDeleteListener != null) {
                int expenseId = model.getId();
                expenseDeleteListener.onExpenseDelete(expenseId);

            }
        });*/
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
            if ("Expense".equals(expense.getMoneyType())) {
                incomeList.add(expense);
            }
        }
        return incomeList;
    }
    /*public void deleteExpense(int position) {
        expenseList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, expenseList.size());
    }*/



}
