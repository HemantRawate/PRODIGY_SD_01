import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TemperatureConverter extends JFrame {
    private JTextField temperatureInput;
    private JComboBox<String> unitInput;
    private JLabel resultLabel;

    public TemperatureConverter() {
        createView();

        setTitle("Temperature Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createView() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel temperatureLabel = new JLabel("Enter temperature:");
        panel.add(temperatureLabel);

        temperatureInput = new JTextField();
        panel.add(temperatureInput);

        JLabel unitLabel = new JLabel("Select unit:");
        panel.add(unitLabel);

        String[] units = {"Celsius (C)", "Fahrenheit (F)", "Kelvin (K)"};
        unitInput = new JComboBox<>(units);
        panel.add(unitInput);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertButtonActionListener());
        panel.add(convertButton);

        resultLabel = new JLabel("Result: ");
        panel.add(resultLabel);
    }

    private class ConvertButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double temperature = Double.parseDouble(temperatureInput.getText());
                String unit = unitInput.getSelectedItem().toString();

                switch (unit) {
                    case "Celsius (C)":
                        double fahrenheitFromCelsius = celsiusToFahrenheit(temperature);
                        double kelvinFromCelsius = celsiusToKelvin(temperature);
                        resultLabel.setText(String.format("Result: %.2f°C = %.2f°F = %.2fK", temperature, fahrenheitFromCelsius, kelvinFromCelsius));
                        break;
                    case "Fahrenheit (F)":
                        double celsiusFromFahrenheit = fahrenheitToCelsius(temperature);
                        double kelvinFromFahrenheit = fahrenheitToKelvin(temperature);
                        resultLabel.setText(String.format("Result: %.2f°F = %.2f°C = %.2fK", temperature, celsiusFromFahrenheit, kelvinFromFahrenheit));
                        break;
                    case "Kelvin (K)":
                        double celsiusFromKelvin = kelvinToCelsius(temperature);
                        double fahrenheitFromKelvin = kelvinToFahrenheit(temperature);
                        resultLabel.setText(String.format("Result: %.2fK = %.2f°C = %.2f°F", temperature, celsiusFromKelvin, fahrenheitFromKelvin));
                        break;
                    default:
                        resultLabel.setText("Unknown unit of measurement.");
                        break;
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid temperature.");
            }
        }
    }

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    public static double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5/9 + 273.15;
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9/5 + 32;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TemperatureConverter().setVisible(true);
        });
    }
}
