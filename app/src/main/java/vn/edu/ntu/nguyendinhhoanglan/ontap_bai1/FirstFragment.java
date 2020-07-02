package vn.edu.ntu.nguyendinhhoanglan.ontap_bai1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;

public class FirstFragment extends Fragment implements View.OnClickListener{

    public static final String KEY = "key";
    EditText edtName, edtBirth, edtPhone, edtAddress;
    ImageView imvBirth;
    Spinner spService;
    RadioGroup rdgPayment;
    String[] arrService;
    ArrayAdapter<String> adtService;
    NavController navController;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        addViews(v);
        // Inflate the layout for this fragment
        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);

                register();
            }
        });
    }

    private void addViews(View v){
        edtName = v.findViewById(R.id.edtName);
        edtAddress = v.findViewById(R.id.edtAddress);
        edtBirth = v.findViewById(R.id.edtBirth);
        edtPhone = v.findViewById(R.id.edtPhone);
        imvBirth = v.findViewById(R.id.imvBirth);
        rdgPayment = v.findViewById(R.id.grpPayment);
        rdgPayment.check(R.id.rdTM);
        imvBirth.setOnClickListener(this);
        spService = v.findViewById(R.id.spService);
        arrService = v.getResources().getStringArray(R.array.dich_vu);
        adtService = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, arrService);
        spService.setAdapter(adtService);

        navController = NavHostFragment.findNavController(FirstFragment.this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imvBirth:
                pickDate();
        }
    }

    private void register(){
        int id = rdgPayment.getCheckedRadioButtonId();
        RadioButton rb = getActivity().findViewById(id);
        String s = rb.getText().toString();
        StringBuilder b = new StringBuilder();
        b.append("Chúc mừng khách hàng: ").append(edtName.getText())
                .append("\nSinh ngày: ").append(edtBirth.getText())
                .append("\nĐã đăng ký thành công dịch vụ: ")
                .append("\n").append(spService.getSelectedItem().toString())
                .append("\nHình thức thanh toán: ").append(s)
                .append("\nChúng tôi sẽ liên lạc với bạn theo số điện thoại: ")
                .append("\n").append(edtPhone.getText());

        Bundle bundle = new Bundle();
        bundle.putCharSequence(KEY, b.toString());
        navController.navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
    }

    private void pickDate() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                StringBuilder builder = new StringBuilder();
                builder.append(dayOfMonth)
                        .append("/")
                        .append(++month)
                        .append("/")
                        .append(year);
                edtBirth.setText(builder.toString());
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}