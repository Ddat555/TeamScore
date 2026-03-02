const countriesData = {
    "Россия": ["Москва", "Санкт-Петербург", "Казань", "Самара", "Новосибирск", "Екатеринбург", "Нижний Новгород"],
    "Германия": ["Берлин", "Гамбург", "Мюнхен", "Франкфурт-на-Майне", "Кёльн"],
    "Италия": ["Рим", "Флоренция", "Венеция", "Милан", "Неаполь"],
    "Франция": ["Париж", "Лион", "Марсель", "Бордо", "Тулуза"]
};


const countrySelect = document.getElementById('countrySelect');
const citySelect = document.getElementById('citySelect');
const resultParagraph = document.getElementById('result');

function populateCountries() {
    const countries = Object.keys(countriesData);

    countries.forEach(country => {
        const option = document.createElement('option');
        option.value = country;
        option.textContent = country;
        countrySelect.appendChild(option);
    });
}

function populateCities(selectedCountry) {
    citySelect.innerHTML = '';
    
    if (!selectedCountry) {
        citySelect.innerHTML = '<option value="" disabled selected>-- Сначала выберите страну --</option>';
        resultParagraph.textContent = '';
        return;
    }
    
    const cities = countriesData[selectedCountry];
    
    if (cities && cities.length > 0) {
        citySelect.innerHTML = '<option value="" disabled selected>-- Выберите город --</option>';

        cities.forEach(city => {
            const option = document.createElement('option');
            option.value = city;
            option.textContent = city;
            citySelect.appendChild(option);
        });
    } else {
        citySelect.innerHTML = '<option value="" disabled>-- Города не найдены --</option>';
    }
    
    resultParagraph.textContent = '';
}

countrySelect.addEventListener('change', function() {
    const selectedCountry = this.value;
    populateCities(selectedCountry);
});

citySelect.addEventListener('change', function() {
    const selectedCountry = countrySelect.value;
    const selectedCity = this.value;
    
    if (selectedCountry && selectedCity) {
        resultParagraph.textContent = `Вы выбрали: ${selectedCity}, ${selectedCountry}`;
    }
});

populateCountries();
populateCities(null);