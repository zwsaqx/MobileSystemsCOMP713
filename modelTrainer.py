import pandas as pd
import tensorflow as tf
from sklearn.model_selection import train_test_split

# import csv data
data = pd.read_csv('gradesData.csv')

X = data[['location', 'workingtime', 'attendance']]
y = data['grade']

# save data to training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# the model
model = tf.keras.Sequential([
    tf.keras.layers.Dense(16, activation='relu', input_shape=(3,)),
    tf.keras.layers.Dense(1)  # grade output
])

model.compile(optimizer='adam', loss='mean_squared_error')

# model training and save output as tflite file
model.fit(X_train, y_train, epochs=100, validation_split=0.2)
converter = tf.lite.TFLiteConverter.from_keras_model(model)
tflite_model = converter.convert()

with open('model.tflite', 'wb') as f:
    f.write(tflite_model)

print("Model trained and saved as model.tflite")
