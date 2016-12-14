/*
 * Java Genetic Algorithm Library (@__identifier__@).
 * Copyright (c) @__year__@ Franz Wilhelmstötter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Author:
 *    Franz Wilhelmstötter (franz.wilhelmstoetter@gmx.at)
 */
package org.jenetics.example.tsp.gpx;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;

import org.testng.annotations.Test;

import org.jenetics.util.IO;

/**
 * @author <a href="mailto:franz.wilhelmstoetter@gmx.at">Franz Wilhelmstötter</a>
 * @version !__version__!
 * @since !__version__!
 */
public class PointTest {

	@Test
	public void jaxb() throws IOException {
		IO.JAXB.register(Link.Model.class);
		IO.JAXB.register(WayPoint.Model.class);

		final WayPoint point = WayPoint.builder()
			.ageOfDGPSAge(3.4)
			.comment("Some point")
			.fix(Fix.DGPS)
			.elevation(234.4)
			.speed(32.4)
			.time(ZonedDateTime.now())
			.links(Arrays.asList(Link.of("http://jenetics.io", "foo", "bar"), Link.of("http://foo.com")))
			.build(43.32, 13.23);

		IO.jaxb.write(point, System.out);
	}

}
