package com.example.project.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project.AfterConnect;
import com.example.project.DataBaseHelper;
import com.example.project.Encrypter;
import com.example.project.R;
import com.example.project.SignIn;
import com.example.project.databinding.FragmentProfileBinding;
import com.example.project.databinding.FragmentProfiletenantBinding;
import com.example.project.ui.home.HomeFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private FragmentProfiletenantBinding binding1;
    public int Signed1 = 0;
    public int Signed2 = 0;
    public int Signed3 = 0;
    public int Signed4 = 0;
    public int Signed5 = 0;
    public int Signed6 = 0;
    public int Signed8 = 0;
    public int Signed9 = 0;
    public int Signed10 = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeFragment.k=0;
        if (AfterConnect.Guest == 1) { // Guest
            Intent intent = new
                    Intent(getContext(), SignIn.class);
            getContext().startActivity(intent);
        }


        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding1 = FragmentProfiletenantBinding.inflate(inflater, container, false);
        View root;

        if (SignIn.i == 1) {
            root = binding.getRoot();
            TextView email = root.findViewById(R.id.profileID);
            EditText agencyName = root.findViewById(R.id.profileAgencyNameEdit);
            EditText password = root.findViewById(R.id.profilePasswordEdit);
            EditText passwordConfirm = root.findViewById(R.id.profilePasswordConfirm);
            EditText country = root.findViewById(R.id.profileCountrySpinner);
            EditText city = root.findViewById(R.id.profileCitySpinner);
            EditText phone = root.findViewById(R.id.profilePhoneEdit);
            TextView passwordConfirm1 = root.findViewById(R.id.confirmPasswordRental);

            Button Done = root.findViewById(R.id.DoneEditing);


            email.setText(SignIn.rental.getRentalId());
            agencyName.setText(SignIn.rental.getRentalName());
            password.setText("");
            passwordConfirm.setText("");
            country.setText(SignIn.rental.getRentalCountry());
            city.setText(SignIn.rental.getRentalCity());
            phone.setText(SignIn.rental.getRentalPhone());

            Done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (phone.getText().toString().length()<10) {
                        Signed4 = 1;
                    } else {
                        Signed4 = 0;
                    }

                    if(20 >= agencyName.getText().toString().length() && 3 <= agencyName.getText().toString().length()){
                        Signed5 = 0;
                    } else{
                        Signed5 = 1;
                    }
                    //Password pattern
                    Pattern special= Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                    Pattern number = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = special.matcher(password.getText().toString());
                    Matcher matcherNumber = number.matcher(password.getText().toString());

                    boolean constainsSymbols = matcher.find();
                    boolean containsNumber = matcherNumber.find();

                    if(15 >= password.getText().toString().length() && password.getText().toString().length() >=8){
                        if(constainsSymbols == true && containsNumber == true){
                            Signed2 = 0;
                        } else{
                            Signed2 = 1;
                        }
                    } else{
                        Signed2 = 1;
                    }

                    if (password.getText().toString().equals(passwordConfirm.getText().toString()) == false) {
                        passwordConfirm1.setText("Passwords must be the same");
                        Signed3 = 1;
                    }else{
                        Signed3 = 0;
                    }

                    if (Signed1==0 && Signed2==0 && Signed3==0 && Signed4==0 && Signed5==0) {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(root.getContext())  ;
                        SignIn.rental.setRentalCity(city.getText().toString());
                        SignIn.rental.setRentalCountry(country.getText().toString());
                        SignIn.rental.setRentalName(agencyName.getText().toString());
                        SignIn.rental.setRentalPassword(Encrypter.EncrypterData512(password.getText().toString()));
                        SignIn.rental.setRentalPhone(phone.getText().toString());
                        dataBaseHelper.editRental(SignIn.rental);
                    }
                }
            });



        }
        else if(AfterConnect.Guest == 1){
            root=null;
            Intent intent = new
                    Intent(getContext(), AfterConnect.class);
            getContext().startActivity(intent);
        }
        else {
            root = binding1.getRoot();
            TextView email = root.findViewById(R.id.profileEmailTenant);
            EditText firstName = root.findViewById(R.id.firstNameTenant);
            EditText lastName = root.findViewById(R.id.lastNameTenant);
            EditText gender = root.findViewById(R.id.genderTenant);
            EditText password = root.findViewById(R.id.profilePasswordTenant);
            EditText passwordConfirm = root.findViewById(R.id.profilePasswordConfirmTenant);
            EditText nation = root.findViewById(R.id.nationTenant);
            EditText occupation = root.findViewById(R.id.occupationTenant);
            EditText size = root.findViewById(R.id.familySizeTenant);
            EditText country = root.findViewById(R.id.countryTenant);
            EditText city = root.findViewById(R.id.cityTenant);
            EditText phone = root.findViewById(R.id.phoneNumberTenant);
            TextView pass = root.findViewById(R.id.confirmErrorTenant);
            Button Done = root.findViewById(R.id.profileBtn);

            email.setText(SignIn.tenant.getTenantId());
            firstName.setText(SignIn.tenant.getTenantFirstName());
            lastName.setText(SignIn.tenant.getTenantLastName());
            gender.setText(SignIn.tenant.getTenantGender());
            password.setText(SignIn.tenant.getTenantPassword());
            passwordConfirm.setText(SignIn.tenant.getTenantPassword());
            nation.setText(SignIn.tenant.getTenantNationality());
            occupation.setText(SignIn.tenant.getTenantOccupation());
            size.setText(SignIn.tenant.getTenantSize());
            country.setText(SignIn.tenant.getTenantCountry());
            city.setText(SignIn.tenant.getTenantCity());
            phone.setText(SignIn.tenant.getTenantPhone());

            Done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Password pattern
                    Pattern special= Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                    Pattern number = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = special.matcher(password.getText().toString());
                    Matcher matcherNumber = number.matcher(password.getText().toString());

                    boolean constainsSymbols = matcher.find();
                    boolean containsNumber = matcherNumber.find();
                    if(15 >= password.getText().toString().length() && password.getText().toString().length() >=8){
                        if(constainsSymbols == true && containsNumber == true){
                            pass.setText("");
                            Signed2 = 0;
                        } else{
                            pass.setText("Password must contain at least a Number and Special characters");
                            Signed2 = 1;
                        }
                    } else{
                        pass.setText(" Password must be 8 to 15 length");
                        Signed2 = 1;
                    }
                    if (password.getText().toString().equals(passwordConfirm.getText().toString()) == false) {
                        pass.setText("Passwords must be the same");
                        Signed3 = 1;
                    }else{
                        pass.setText("");
                        Signed3 = 0;
                    }

                    if (phone.getText().toString().length()<10) {
                        Signed4 = 1;
                    } else {
                        Signed4 = 0;
                    }
                    if(20 >= firstName.getText().toString().length() && 3 <= firstName.getText().toString().length()){
                        Signed5 = 0;
                    } else{
                        Signed5 = 1;
                    }
                    if(20 >= lastName.getText().toString().length() && 3 <= lastName.getText().toString().length()){
                        Signed6 = 0;
                    } else{
                        Signed6 = 1;
                    }

                    if (occupation.getText().toString().equals("")) {
                        Signed8 = 1;
                    } else {
                        Signed8 = 0;
                    }

                    if (size.getText().toString().equals("")) {
                        Signed9 = 1;
                    } else {
                        Signed9 = 0;
                    }

                    if(20 >= occupation.getText().toString().length()){
                        Signed10 = 0;
                    } else{
                        Signed10 = 1;
                    }
                    if (Signed1==0 && Signed2==0 && Signed3==0 && Signed4==0 && Signed5==0 && Signed6==0
                            && Signed8==0 && Signed9==0 && Signed10==0) {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(root.getContext());
                        SignIn.tenant.setTenantCity(city.getText().toString());
                        SignIn.tenant.setTenantCountry(country.getText().toString());
                        SignIn.tenant.setTenantFirstName(firstName.getText().toString());                        SignIn.tenant.setTenantLastName(lastName.getText().toString());
                        SignIn.tenant.setTenantLastName(lastName.getText().toString());
                        SignIn.tenant.setTenantPassword(Encrypter.EncrypterData512(password.getText().toString()));
                        SignIn.tenant.setTenantPhone(phone.getText().toString());
                        SignIn.tenant.setTenantOccupation(occupation.getText().toString());
                        SignIn.tenant.setTenantGender(gender.getText().toString());
                        SignIn.tenant.setTenantNationality(nation.getText().toString());
                        SignIn.tenant.setTenantSize(size.getText().toString());
                        dataBaseHelper.editTenant(SignIn.tenant);
                    }

                }
            });
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}