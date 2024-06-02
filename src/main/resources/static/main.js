document.querySelectorAll('.status').forEach(function(select) {
    select.addEventListener('change', function() {
        var reservationId = this.getAttribute('data-reservation-id');
        var newStatus = this.value;

        fetch('/reservation/update/' + reservationId, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                status: newStatus
            })
        })
            .then(response => response.json())
            .then(data => console.log(data))
            .catch((error) => {
                console.error('Error:', error);
            });
    });
});