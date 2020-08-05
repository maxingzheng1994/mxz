const form = document.querySelector('.form-overview');

form.addEventListener('slSubmit', event => {
    const formData = event.detail.formData;
    console.log("======="+formData)
    let output = '';

    //
    // Example 1: Post data to a server and wait for a JSON response
    //
    fetch('/people2/save', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(result => {
            console.log('Success:', result);
        })
        .catch(error => {
            console.error('Error:', error);
        });

    //
    // Example 2: Output all form control names + values
    //
    for (const entry of formData.entries()) {
        output += `${entry[0]}: ${entry[1]}\n`;
    }
    alert(output);

    //
    // Example 3: Get all form controls that were serialized as
    // an array of HTML elements
    //
    console.log(event.detail.formControls);
});