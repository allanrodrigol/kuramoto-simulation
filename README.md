# Kuramoto simulator

This project is a Java-based implementation of a mathematical synchronization model known as **Kuramoto Model**.

## Introduction

Synchronization is a research areas of nonlinear dynamical systems, which aims to understand the trends of synchronously operating systems. One of the most successful attempts to understand and reproduce the synchronization phenomenon is known as the Kuramoto model [1]. 

Kuramoto model exhibits the emergence of collective behavior in a coupled phase oscillator network. An oscillator is a device that autonomously produces repeated signals. In particular, phase oscillators have constant amplitudes, in which each phase value represents the state of an oscillator at a given time. The space-state of an oscillator can be defined by a circular interval <img src="https://render.githubusercontent.com/render/math?math=[0,2\pi]">, where <img src="https://render.githubusercontent.com/render/math?math=0 = 2\pi">. Each oscillator emits a pulse to its neighboring oscillators whenever its phase reaches a certain threshold [2].

## Kuramoto model

Formally, the Kuramoto model consists of a population of *N* globally coupled phase oscillators. Each oscillator *i* has an individual phase <img src="https://render.githubusercontent.com/render/math?math=\theta_i"> and a natural frequency <img src="https://render.githubusercontent.com/render/math?math=\omega_i">. The Kuramoto model captures the emergence of the collective behavior in coupled oscillator network by gradual adjustments of the rhythms (frequencies) of the oscillators toward a mean-field represented by <img src="https://render.githubusercontent.com/render/math?math=\Omega"> [1]. These adjustments occur iteratively when oscillators receive new pulses from neighboring oscillators through sinusoidal couplings.

The collective dynamics of the oscillator network make the phases become more similar over time, governed by following equation [3]\:

<img src="https://render.githubusercontent.com/render/math?math=\frac{d\theta_i}{d_t} = \omega_i + \frac{K}{N} \sum_{j=1}^{N} \sin(\theta_j - \theta_i)">

where *K* is the global coupling strength and *N* is the number of oscillators in the network. The frequencies <img src="https://render.githubusercontent.com/render/math?math=\omega_i"> are usually represented by some distribution function <img src="https://render.githubusercontent.com/render/math?math=g(\omega)">. When the frequency distribution is too dissimilar with respect to the coupling strength, the oscillators are unable to synchronize their phases and hence the system behaves incoherently over time. Therefore, a coupled oscillator network is able to reach a phase-synchronized state if there exists a coupling strength *K* enough regarding the heterogeneity of the natural frequencies of the oscillators.

## Order parameter

Synchronization can also be represented in a more convenient way from the following complex-valued order parameter, according to equation below [2]:

<img src="https://render.githubusercontent.com/render/math?math=r e^{i\psi} = \frac{1}{N}\sum_{j=1}^{N}e^{i\theta_{j}}">,

where <img src="https://render.githubusercontent.com/render/math?math=0 \leq r(t) \leq 1"> measures the coherence level among all oscillators and <img src="https://render.githubusercontent.com/render/math?math=\psi(t)"> is the average phase of the system. The values <img src="https://render.githubusercontent.com/render/math?math=r(t) \approx 0"> and <img src="https://render.githubusercontent.com/render/math?math=r(t) \approx 1"> indicate absence of synchronization and the system being in a phase-synchronized state, respectively.

## Build

This project and uses [Apache Maven](https://maven.apache.org/) builder and requires JDK 11 or higher. Run `maven clean install` to build the project. The compiled files will be stored in the `target/` directory.

## Running application

This is a [Swing Java](https://en.wikipedia.org/wiki/Swing_\(Java\)) application. Run `Main` class for starting the application. This application shows the synchronization dynamics in a coupled oscillator network, focusing on the coupling strength dominance over the natural frequencies. First you need to configure a complex oscillator network with homogeneous coupling strength. 

After starting, each oscillator rotates around the same path without colliding. Each oscillator has a phase angle $\theta_i$ and a preferred natural rotation frequency $\omega_i$ representing the rotation speed. The movements of coupled oscillators are influenced by all of them, when the coupling strength is sufficient for interfering in the frequency $\omega_i$ of each oscillator.


## References
1. Kuramoto, Y. (1975). Self-entrainment of a population of coupled nonlinear oscillators. International Symposium on Mathematical Problems in Theoretical Physics, Lecture Notes in Physics, Vol. 39, 420–422, Springer, New York, NY, USA.
2. Acebrón, J. A., Bonilla, L. L., Pérez-Vicente, C. J., Ritort, F., & Spigler, R. (2005). The kuramoto model: A simple paradigm for synchronization phenomena. Rev. Mod. Phys., Vol. 77, 137–185, American Physical Society.
3. Kuramoto, Y. (1984). Chemical oscillations, waves, and turbulence. Springer-Verlag, New York, NY, USA.
