package edu.packt.neuralNet;

import java.util.ArrayList;

import edu.packt.neuralNet.math.RandomNumberGenerator;

public class Neuron {
	
	// Neuron is the part of the neural network which takes the inputs
	// multiplies it with their weights, totals it and then passes it 
	// into an activation function. It can have multiple inputs and 
	// one output 

	protected ArrayList<Double> weight;
	protected ArrayList<Double> input;
	private Double output;							// 	final output
	private Double outputBeforeActivation;			//	Before we send to our activation function
	private int numOfInputs = 0;
	protected Double bias = 1.0;
	private IActivationFunction activationFunction;

	// regular constructor for our neuron
	public Neuron(int numberOfInputs, IActivationFunction iaf) {
		numOfInputs = numberOfInputs;
		weight = new ArrayList<>(numberOfInputs + 1);//	Add +1 for the weight of the bias
		input = new ArrayList<>(numberOfInputs);
		activationFunction = iaf;
	}

	// sets the values of the weights of the inputs to random values
	public void init() {
		for (int i = 0; i < numOfInputs ; i++) {

			double newWeight = RandomNumberGenerator.GenerateNext();

			try {
				this.weight.set(i, newWeight);
			} catch (IndexOutOfBoundsException e) {
				this.weight.add(newWeight);
			}

		}

	}
	// does the calculation before passing to the activation function
	public void calc(){
		
		outputBeforeActivation = 0.0;

		if (numOfInputs > 0) {
			// check to make sure input and weights exist
			if (input != null && weight != null) {
				for (int i = 0; i < numOfInputs; i++) {
					// ? is a conditional statement. order goes (condition)?true:false 
					// += just adds to original variable. x += y <=> x = x + y
					outputBeforeActivation+= (i==numOfInputs?bias:input.get(i) * weight.get(i));
				}
			}
		}
		// sends to final activation function
		output = activationFunction.calc(outputBeforeActivation);
	}



}


	
	
