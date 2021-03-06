/* Set up the sign up form */
function signupSetup() {

    /* Set on-click to slide open the sign up form */
    $('#signUpButton').click(function() {
        $('#signUp').slideToggle('slow');
    });

    /* Set the create account button on-click to send an AJAX post*/
    $('#signUp').submit(function(event) {

        event.preventDefault();

        // Get the data from form
        let formData = $('#signUp').serialize();

        // Send post AJAX to create account
        $.post('/createAccount', formData, function(data) {
            alert('User created');

            // Reset form
            $('#signUp').each(function() {
                this.reset();
                $('#signUp').slideToggle('slow');
            });
        })

        .fail(function(response) {
            alert(response.responseText);
        });

        return false;
    });
}


/* Set up the sign in form */
function signInSetup() {

    // Set the sign in button on-click function
    $('#loginForm').submit(function(event) {

        event.preventDefault();
        console.log(5 + 6);

        // Get the data from form
        let formData = $('#loginForm').serialize();

        // Send post AJAX
        $.post('/signIn', formData, function(data) {
            window.location.replace('/about');


        })

        .fail(function(response) {
            alert(response.responseText);
        });

        return false;
    })
};


/* Set up the page */
$(document).ready(function() {

    $('#courses').hide();
    $('#myMarks').hide();
    $('#profile').hide();
    $('#logout').hide();
    signupSetup();
    signInSetup();
})
