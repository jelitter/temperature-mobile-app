# Programming Mobile Devices Assignment 1

### Overview

> 1. Create yourownApp icon in 3 different resolutions (it doesn’t need to be beautiful!). Use Photoshop or gimp etc. **10 marks**

Icons created and included in the `mipmap` folders:

![Icons](Assignment/img/screen5.jpg 'Icons')

**2. Create a main Activity with at least one each of: TextView, EditText and Button components. Use at least two layout managers and embed one inside another eg. one LinearLayout inside another LinearLayout.**

Used a scrollview as a main container, inside there are several linear and constraint layouts. Used layout_weight to get elements to use proportional spaces. Elements auto-adjust on soft-keyboard activation / deactivation:

![No keyboard](Assignment/img/screen1.jpg 'Without keyboard') ![Keyboard](Assignment/img/screen2.jpg 'With keyboard')

**3. Process the events on at least one of the GUI components in 1) above. The App should perform the logic in the 'Overview' above.**

-   Input fields:

    -   when focusing each one, up/down buttons text is updated (with C,F or K).
    -   when entering text in any of them, the other two are updated with the corresponding degrees.

-   Up / down buttons:

    -   increase or decrease selected degrees by 1

-   Email field:

    -   when entering text, SEND button is enabled or disabled (and style changed) depending on wether the entered email address is valid or not.

    ![Invalid Email](Assignment/img/screen3.jpg 'Invalid Email') ![Valid Email](Assignment/img/screen4.jpg 'Valid Email')

**4. A trigger on the first Activity launches a second Activity and passes some data to it for display.**

Button SHARE starts the second activity to share temperature results via email.

**5. Use method onSaveInstanceState() to save any variables during orientation changes to be then restored.**

Temperatures are saved and then loaded upon device rotation. UI is updated accordingly.

**6. Using a coding standard e.g. Commented code, private variables, automatic variables, use extra classes where appropriate etc. (pick only one source file to show comments etc.). List of Class files and what they do. Evaluation - how did it work out for you, what mistakes did you make, what did you learn.**

Comments included in all 3 `.java` files.

**Other features:**

-   Used strings, colors and drawables
