//My First Android Calculator App
//source: https://github.com/obaro/SampleCalculator/blob/master/app/src/main/java/com/sample/foo/samplecalculator/MainActivity.java
//soon to be improved! Stay tuned
package izzanfakhrilislam.kalkulatorsederhana;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import izzanfakhrilislam.kalkulatorsederhana.databinding.ActivityMainBinding;

import java.text.DecimalFormat;



public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char SQRT = '√';

    private boolean StartOfNextOperand = false;


    private char CURRENT_ACTION;

    private double valueOne = Double.NaN;
    private double valueTwo;
    private double squareRoot;

    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        decimalFormat = new DecimalFormat("#.##########");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + ".");
            }
        });

        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });

        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });

        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });

        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });

        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });

        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });

        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });

        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });

        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });

        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    computeCalculation();
                    CURRENT_ACTION = ADDITION;
                    binding.infoTextView.setText(decimalFormat.format(valueOne) + "+");
                    binding.editText.setText(null);
                } catch (Exception e) {
                    ExceptionHandling();

                }
            }
        });

        binding.buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    computeCalculation();
                    CURRENT_ACTION = SUBTRACTION;
                    binding.infoTextView.setText(decimalFormat.format(valueOne) + "-");
                    binding.editText.setText(null);
                }
                catch (Exception e) {
                    ExceptionHandling();
                }
            }
        });

        binding.buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    computeCalculation();
                    CURRENT_ACTION = MULTIPLICATION;
                    binding.infoTextView.setText(decimalFormat.format(valueOne) + "*");
                    binding.editText.setText(null);
                }
                catch (Exception e) {
                    ExceptionHandling();
                }
            }
        });

        binding.buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    computeCalculation();
                    CURRENT_ACTION = DIVISION;
                    binding.infoTextView.setText(decimalFormat.format(valueOne) + "/");
                    binding.editText.setText(null);
                }
                catch (Exception e) {
                    ExceptionHandling();
                }
            }
        });

        binding.buttonRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    computeCalculation();
                    CURRENT_ACTION = SQRT;
                    binding.infoTextView.setText("sqrt" + "(" + decimalFormat.format(valueOne) + ")");
                    //valueTwo = Double.NaN;
                    binding.editText.setText(null);
                } catch (Exception e) {
                    ExceptionHandling();
                }

            }
        });

        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (CURRENT_ACTION == SQRT) {
                        binding.infoTextView.setText(binding.infoTextView.getText().toString() + "=" + decimalFormat.format(valueOne));
                        binding.editText.setText(String.valueOf(valueOne));
                    }
                    else {
                        computeCalculation();
                        binding.infoTextView.setText(binding.infoTextView.getText().toString() + decimalFormat.format(valueTwo) + "=" + decimalFormat.format(valueOne));
                        binding.editText.setText(String.valueOf(valueOne));
                        CURRENT_ACTION = '0';
                    }
                } catch (Exception e) {
                    ExceptionHandling();
                }
            }
        });

        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editText.getText().length() > 0) {
                    CharSequence currentText = binding.editText.getText();
                    binding.editText.setText(currentText.subSequence(0, currentText.length() - 1));
                } else {
                    valueOne = Double.NaN;
                    valueTwo = Double.NaN;
                    binding.editText.setText(null);
                    binding.infoTextView.setText(null);
                }
            }
        });
    }

    private void ExceptionHandling() {
		CURRENT_ACTION = 0;
		binding.editText.setText(null);	
        binding.infoTextView.setText("Error");
        StartOfNextOperand = false;
        
    }

    private void computeCalculation() {
        if (!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(binding.editText.getText().toString());
            binding.editText.setText(null);

            if (CURRENT_ACTION == ADDITION) {
                valueOne = this.valueOne + valueTwo;
            } else if (CURRENT_ACTION == SUBTRACTION) {
                valueOne = this.valueOne - valueTwo;
            } else if (CURRENT_ACTION == MULTIPLICATION) {
                valueOne = this.valueOne * valueTwo;
            } else if (CURRENT_ACTION == DIVISION) {
                valueOne = this.valueOne / valueTwo;
            } else if (CURRENT_ACTION == SQRT) {
                valueOne = Math.sqrt(this.valueOne);
            }
        }
        else {
            try {
                valueOne = Double.parseDouble(binding.editText.getText().toString());
            } catch (Exception e) {
            }
        }
    }
}