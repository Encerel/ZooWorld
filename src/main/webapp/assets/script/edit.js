function toggleEdit(event) {
    event.preventDefault();

    const fieldset = document.querySelector('fieldset');
    fieldset.toggleAttribute('disabled');
};