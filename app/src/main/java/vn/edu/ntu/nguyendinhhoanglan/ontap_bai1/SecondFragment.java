package vn.edu.ntu.nguyendinhhoanglan.ontap_bai1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {
    TextView textView;
    NavController navController;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        textView = v.findViewById(R.id.txtSecond);
        Bundle bundle = getArguments();
        String s = bundle.getString(FirstFragment.KEY);
        textView.setText(s);
        navController = NavHostFragment.findNavController(this);
        // Inflate the layout for this fragment
        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(SecondFragment.this)
//                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
                navController.navigateUp();
            }
        });
    }
}