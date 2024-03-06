const baseUrl = 'http://localhost:3110/api'
const headers = { 'Content-Type': 'application/json' }

output.selectCollection = (collection) => {
	const url = `${baseUrl}/config`
	const data = { 'mock': { 'collections': { 'selected': collection } } }

	http.request(url, {
		method: 'PATCH',
		headers: headers,
		body: JSON.stringify(data)
	})
}

output.selectRouteVariant = (routeVariant) => {
	const url = `${baseUrl}/mock/custom-route-variants`
	const data = { 'id' : routeVariant }

	http.request(url, {
		method: 'POST',
		headers: headers,
		body: JSON.stringify(data)
	})
}

output.restoreRouteVariants = () => {
	const url = `${baseUrl}/mock/custom-route-variants`

	http.request(url, {
		method: 'DELETE',
		headers: headers
	})
}
