package com.crystal.flexin.adapter;


/**
 * Created by basaile92 on 01/04/2017.
 */

public class MaterielListAdapter /*extends RecyclerView.Adapter<MaterielListAdapter.ViewHolder>*/ {
/*
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView MaterielImageView;
        private LinearLayout MaterielEtatLayout;
        private TextView MaterielPlatformText;
        private TextView MaterielNameText;

        public ViewHolder(View view) {
            super(view);
            this.MaterielImageView = ((ImageView) view.findViewById(R.id.materielImageView));
            this.MaterielEtatLayout = ((LinearLayout) view.findViewById(R.id.materielEtatLayout));
            this.MaterielPlatformText = ((TextView) view.findViewById(R.id.materielPlatformText));
            this.MaterielNameText = ((TextView) view.findViewById(R.id.materielNameText));
        }

        public ImageView getMaterielImageView() {
            return MaterielImageView;
        }

        public LinearLayout getMaterielEtatLayout() {
            return MaterielEtatLayout;
        }

        public TextView getMaterielPlatformText() {
            return MaterielPlatformText;
        }

        public TextView getMaterielNameText() {
            return MaterielNameText;
        }
    }

    private List<Materiel> materielList;

    public MaterielListAdapter(List<Materiel> materielList){

        this.materielList = materielList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_materiel_item, parent, false);
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
    public void onBindViewHolder(MaterielListAdapter.ViewHolder holder, int position) {

        ImageView materielImageView = holder.getMaterielImageView();
        LinearLayout MaterielEtatLayout = holder.getMaterielEtatLayout();
        TextView MaterielPlatformText = holder.getMaterielPlatformText();
        TextView MaterielNameText = holder.getMaterielNameText();
        Materiel materiel = this.materielList.get(position);

        //Photo assignment
        //TODO if(materiel.getPhoto().equals(""))
            //TODO setImageView

        MaterielManager.setEtatColor(MaterielEtatLayout, materiel.getEtat());

        MaterielPlatformText.setText(materiel.getPlateforme());

        MaterielNameText.setText(materiel.getNom());
    }

    @Override
    public int getItemCount() {
        return this.materielList.size();
    }*/
}
