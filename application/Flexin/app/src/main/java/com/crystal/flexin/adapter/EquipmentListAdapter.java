package com.crystal.flexin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crystal.flexin.R;
import com.crystal.flexin.manager.EquipmentManager;
import com.crystal.flexin.resources.Equipment;

import java.util.List;


/**
 * Created by basaile92 on 01/04/2017.
 */

public class EquipmentListAdapter extends RecyclerView.Adapter<EquipmentListAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView equipmentImageView;
        private LinearLayout equipmentEtatLayout;
        private TextView equipmentPlatformText;
        private TextView equipmentNameText;

        public ViewHolder(View view) {
            super(view);
            this.equipmentImageView = ((ImageView) view.findViewById(R.id.equipmentImageView));
            this.equipmentEtatLayout = ((LinearLayout) view.findViewById(R.id.equipmentEtatLayout));
            this.equipmentPlatformText = ((TextView) view.findViewById(R.id.equipmentPlatformText));
            this.equipmentNameText = ((TextView) view.findViewById(R.id.equipmentNameText));
        }

        public ImageView getEquipmentImageView() {
            return equipmentImageView;
        }

        public LinearLayout getEquipmentEtatLayout() {
            return equipmentEtatLayout;
        }

        public TextView getEquipmentPlatformText() {
            return equipmentPlatformText;
        }

        public TextView getEquipmentNameText() {
            return equipmentNameText;
        }
    }

    private List<Equipment> equipmentList;

    public EquipmentListAdapter(List<Equipment> equipmentList){

        this.equipmentList = equipmentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_equipement_item, parent, false);
        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                int itemPosition = ((RecyclerView)parent).getChildLayoutPosition(view);
                //TODO changer d'activité
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EquipmentListAdapter.ViewHolder holder, int position) {

        ImageView equipmentImageView = holder.getEquipmentImageView();
        LinearLayout equipmentEtatLayout = holder.getEquipmentEtatLayout();
        TextView equipmentPlatformText = holder.getEquipmentPlatformText();
        TextView equipmentNameText = holder.getEquipmentNameText();
        Equipment equipment = this.equipmentList.get(position);

        //Photo assignment
        //TODO if(equipment.getPhoto().equals(""))
            //TODO setImageView

        EquipmentManager.setEtatColor(equipmentEtatLayout, equipment.getEtat());

        //TODO equipmentPlatformText.setText(equipment.getPlateformeId) etc...;

        equipmentNameText.setText(equipment.getNom());
    }

    @Override
    public int getItemCount() {
        return this.equipmentList.size();
    }
}
