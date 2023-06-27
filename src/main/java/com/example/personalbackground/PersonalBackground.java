    package com.example.personalbackground;

    import android.Manifest;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.content.pm.PackageManager;
    import android.graphics.Bitmap;
    import android.os.Bundle;
    import android.provider.MediaStore;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.RadioButton;
    import android.widget.RadioGroup;
    import android.widget.Spinner;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.app.ActivityCompat;
    import androidx.core.content.ContextCompat;

    import com.example.personalbackground.Educational;

    public class PersonalBackground extends AppCompatActivity {

        private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
        private static final int CAMERA_REQUEST_CODE = 101;

        private static final String SHARED_PREF_NAME = "myCode";

        SharedPreferences shareToReport;

        private ImageView photoImageView;
        private EditText nameEditText;
        private EditText phoneEditText;
        private EditText heightEditText;
        private EditText weightEditText;
        private Spinner relationshipSpinner;
        private EditText pagibigEditText;
        private EditText philhealthEditText;
        private EditText tinEditText;
        private EditText gsisEditText;
        private EditText contactEditText;
        private EditText emailEditText;

        private EditText emergencyContactNameEditText;

        private String gender;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setTitle("Final Project");

            photoImageView = findViewById(R.id.photoImageView);
            nameEditText = findViewById(R.id.nameEditText);
            phoneEditText = findViewById(R.id.phoneEditText);
            heightEditText = findViewById(R.id.heightEditText);
            weightEditText = findViewById(R.id.weightEditText);
            relationshipSpinner = findViewById(R.id.relationshipSpinner);
            pagibigEditText = findViewById(R.id.pagibigEditText);
            philhealthEditText = findViewById(R.id.philhealthEditText);
            tinEditText = findViewById(R.id.tinEditText);
            gsisEditText = findViewById(R.id.gsisEditText);
            contactEditText = findViewById(R.id.emergencyContactEditText);
            emailEditText = findViewById(R.id.emailEditText);
            emergencyContactNameEditText = findViewById(R.id.emergencyContactNameEditText);

            shareToReport = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

            Button captureButton = findViewById(R.id.captureButton);
            captureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkCameraPermission()) {
                        openCamera();
                    } else {
                        requestCameraPermission();
                    }
                }
            });

            Button submitButton = findViewById(R.id.submitButton);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = nameEditText.getText().toString();
                    String phone = phoneEditText.getText().toString();
                    String height = heightEditText.getText().toString();
                    String weight = weightEditText.getText().toString();
                    String contact = contactEditText.getText().toString();
                    String email = emailEditText.getText().toString();
                    String relationship = relationshipSpinner.getSelectedItem().toString();
                    String pagibig = pagibigEditText.getText().toString();
                    String philhealth = philhealthEditText.getText().toString();
                    String tin = tinEditText.getText().toString();
                    String gsis = gsisEditText.getText().toString();
                    String contactname = emergencyContactNameEditText.getText().toString();


                    SharedPreferences.Editor inputToReport = shareToReport.edit();
                    inputToReport.clear();
                    inputToReport.putString("Name", name);
                    inputToReport.putString("Phone", phone);
                    inputToReport.putString("Height", height);
                    inputToReport.putString("Weight", weight);
                    inputToReport.putString("Contact", contact);
                    inputToReport.putString("Email", email);
                    inputToReport.putString("Gender", gender);
                    inputToReport.putString("Relationship", relationship);
                    inputToReport.putString("Pagibig", pagibig);
                    inputToReport.putString("PhilHealth", philhealth);
                    inputToReport.putString("Tin", tin);
                    inputToReport.putString("Gsis", gsis);
                    inputToReport.putString("ContactName", contactname);

                    if (validateForm()) {
                        RadioGroup civilStatusRadioGroup = findViewById(R.id.civilStatusRadioGroup);
                        int selectedRadioButtonId = civilStatusRadioGroup.getCheckedRadioButtonId();
                        String civilStatus = "";
                        if (selectedRadioButtonId != -1) {
                            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                            civilStatus = selectedRadioButton.getText().toString();
                            inputToReport.putString("CivilStatus", civilStatus);

                        }
                        inputToReport.apply();
                        submitForm();
                    } else {
                        Toast.makeText(PersonalBackground.this, "Please fill in all necessary information.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            RadioGroup genderRadioGroup = findViewById(R.id.genderRadioGroup);
            genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton selectedRadioButton = findViewById(checkedId);
                    gender = selectedRadioButton.getText().toString();
                }
            });
        }

        private boolean checkCameraPermission() {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        }

        private void requestCameraPermission() {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }

        private void openCamera() {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
                if (data != null && data.getExtras() != null) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    if (photo != null) {
                        photoImageView.setImageBitmap(photo);
                    }
                }
            }
        }

        private boolean validateForm() {
            String name = nameEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String height = heightEditText.getText().toString().trim();
            String weight = weightEditText.getText().toString().trim();
            String contact = contactEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();

            if (gender == null || gender.isEmpty()) {
                return false;
            }

            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return false;
            }

            return !name.isEmpty() && !phone.isEmpty() && !height.isEmpty() && !weight.isEmpty() && !contact.isEmpty();
        }

        private void submitForm() {
            Toast.makeText(PersonalBackground.this, "Form submitted proceeding to next page.", Toast.LENGTH_SHORT).show();
            SharedPreferences shareToReport = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
            Log.d("Gender", shareToReport.getString("Gender", ""));

            Intent intent = new Intent(PersonalBackground.this, Educational.class);
            startActivity(intent);
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(PersonalBackground.this, "Camera permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
