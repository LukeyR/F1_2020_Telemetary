# WIP

# F1 2020 Telemetry

This is a small side project of mine which, when complete, will read the telemetry broadcast by the **F1 2020** game. It will be my task to create a beautiful, fast and responsive UI in which all data will be display apprioprietly.

Along with a beautiful UI, i plan on incoporating some enthusiast features, such as live graphs for braking and throttle application, live corner speeds, amongst many more, to help those that want to improve the race do so.

To see which features i have completed, please see the commit history of this project, as this will specify what has been completed so far.

## How to run
To run the program, you should run Client.class. If running from a command line, navigate to the folder containing Client.class, and run `java Class`. You can use the following arguments to specify a custom port:

    -p XXXXX or -port XXXXX | Specify a custom port for the program to listen on.

From within the game, you should navigate to your telemetry settings (_Game Options_ > _Settings_ > _Telemetry Settings_), and follow the steps in [this guide](https://www.simracingtelemetry.com/help/F12020/).

- If you are running the telemetry on another machine, you should replace the `UDP IP Address` with the local IPv4 address of the machine the code will run on, not the machine running the game's IPv4 address. Finding this is different on every OS, and there are plentiful guide to find this online.
- Ensure the `port` is set as 20777, unless you have chosen a custom port, in which that should go here instead.
- Whilst not necesary, you will recieve more responsive feedback from the program if you set the UDP send rate to 60Hz, however this can be more demanding if you are running on a slower network/machine.

For help regarding this application, please feel free to raise an issue in the issues tab above.

 
